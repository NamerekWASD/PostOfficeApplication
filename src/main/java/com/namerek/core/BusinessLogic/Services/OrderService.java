package com.namerek.core.BusinessLogic.Services;

import com.namerek.core.BusinessLogic.Interfaces.IOrderService;
import com.namerek.core.DTOs.LightOrderDTO;
import com.namerek.core.DTOs.OrderDTO;
import com.namerek.core.DTOs.PackageDTO;
import com.namerek.core.DataAccess.DBContext;
import com.namerek.core.Entities.Order;
import com.namerek.core.Entities.Package;
import com.namerek.core.Helpers.Utils.PackageProperties;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.UnitOfWorks.GenericRepository;
import com.namerek.core.UnitOfWorks.IGenericRepository;

import java.util.*;

public class OrderService implements IOrderService {
    private static final int NON_POST_OFFICE_COST = 30;
    private static final int NOT_SAME_CITY_COST = 100;
    private static final int NOT_SAME_COUNTRY_COST = 200;
    private static final double WIGHT_AMPLIFIER = 0.01;
    private static final double SIZE_AMPLIFIER = 0.01;
    private static final long MIN_TRACK_NUMBER = 1_000_000_000_000_000L;
    private static final long MAX_TRACK_NUMBER = 9_999_999_999_999_999L;
    IGenericRepository<Order, Long> orderRepository;
    IGenericRepository<Package, Long> packageRepository;
    ModelMapperConverter<Order, OrderDTO> mapper;
    ModelMapperConverter<Package, PackageDTO> packageMapper;

    public OrderService(){
        orderRepository = new GenericRepository<>(Order.class);
        packageRepository = new GenericRepository<>(Package.class);
        mapper = new ModelMapperConverter<>(Order.class, OrderDTO.class);
        packageMapper = new ModelMapperConverter<>(Package.class, PackageDTO.class);
    }
    public OrderService(DBContext dbContext) {
        orderRepository = new GenericRepository<>(Order.class, dbContext);
        packageRepository = new GenericRepository<>(Package.class, dbContext);
        mapper = new ModelMapperConverter<>(Order.class, OrderDTO.class);
        packageMapper = new ModelMapperConverter<>(Package.class, PackageDTO.class);
    }
    @Override
    public long addOrder(OrderDTO order, List<PackageDTO> packages, List<PackageProperties> packagePackageProperties) {
        order.setCost(calculateCost(order, packages, packagePackageProperties));
        order.setStatus(Status.ORDER_SEND.getString());
        order.setDateOfCreation(new Date());

        long trackNumber = generateTrackNumber();
        order.setTrackNumber(trackNumber);
        orderRepository.save(mapper.toA(order));

        mergePackagesAndSave(order, packages, packagePackageProperties);

        return trackNumber;
    }

    private void mergePackagesAndSave(OrderDTO order, List<PackageDTO> packages, List<PackageProperties> packagePackageProperties){
        for (int i = 0; i < packages.size(); i++) {
            PackageDTO _package = packages.get(i);
            PackageProperties packageProperties = packagePackageProperties.get(i);

            _package.setSize(packageProperties.toString());
            _package.setOrder(order);
            packageRepository.save(packageMapper.toA(_package));
        }
    }
    private long generateTrackNumber() {
        return new Random().nextLong(MIN_TRACK_NUMBER, MAX_TRACK_NUMBER);
    }

    @Override
    public List<OrderDTO> getOrders() {
        return mapper.toBList(orderRepository.findAll());
    }

    @Override
    public List<LightOrderDTO> getLightOrders(){
        List<LightOrderDTO> lightOrderList = new ArrayList<>();
        for (var order :
                getOrders()) {
            var lightOrder = new LightOrderDTO(
                    order.getTrackNumber(),
                    order.getSender().getPhoneNumber(),
                    order.getReceiver().getPhoneNumber(),
                    order.getDeparturePoint().toString(),
                    order.getDestination().toString(),
                    order.getStatus(),
                    order.getDateOfCreation());
            if(order.getCell() != null){
                lightOrder.setCell(order.getCell().getUniqueCode());
            }
            lightOrderList.add(lightOrder);
        }
        return lightOrderList;
    }

    @Override
    public OrderDTO getById(long id) {
        try {
            return mapper.toB(orderRepository.find(id));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void editOrder(OrderDTO newOrder) {
        orderRepository.save(mapper.toA(newOrder));
    }
    @Override
    public void editPackages(List<PackageDTO> packages){
        if (packages != null) {
            for (var _package :
                    packages) {
                packageRepository.save(packageMapper.toA(_package));
            }
        }
    }

    @Override
    public void deleteOrder(OrderDTO order) {
        orderRepository.delete(mapper.toA(order));
    }

    @Override
    public List<PackageDTO> getPackages(long trackNumber) {
        List<PackageDTO> packages = packageMapper.toBList(packageRepository.findAll());

        if(packages == null || packages.size() == 0) return new ArrayList<>();

        for (var _package:
                packages) {
            if(_package.getOrder().getTrackNumber() == trackNumber){
                packages.add(_package);
            }
        }
        return packages;
    }

    private double calculateCost(OrderDTO order, List<PackageDTO> packages, List<PackageProperties> packagePropertiesList) {
        double orderCost = order.getBaseCost();

        for (var _package :
                packages){
            orderCost += (WIGHT_AMPLIFIER * _package.getWeight());
        }
        for (var _package :
                packagePropertiesList) {
            orderCost += (SIZE_AMPLIFIER *
                            (_package.getLength() * _package.getHeight() * _package.getWidth()));

        }

        if (!Objects.equals(order.getDestination().getCountry(),
                order.getDeparturePoint().getCountry())) {
            orderCost += NOT_SAME_COUNTRY_COST;
            return orderCost;
        }
        if (!Objects.equals(order.getDestination().getCity(),
                order.getDeparturePoint().getCity())) {
            orderCost += NOT_SAME_CITY_COST;
        }
        if (!order.getDestination().isPostOffice()) {
            orderCost += NON_POST_OFFICE_COST;
        }
        return orderCost;
    }

    private enum Status {
        ORDER_SEND {
            public String getString() {
                return "Order send.";
            }
        },
        PACKAGE_IN_DEPARTURE_OFFICE {
            public String getString() {
                return "Package in departure office.";
            }
        },
        PACKAGE_DEPARTURE_FROM_OFFICE {
            public String getString() {
                return "Package departure from office.";
            }
        },
        PACKAGE_IN_THE_WAY {
            public String getString() {
                return "Package in the way.";
            }
        },
        PACKAGE_RiCH_DESTINATION {
            public String getString() {
                return "Package rich destination.";
            }
        };

        public abstract String getString();
    }
}

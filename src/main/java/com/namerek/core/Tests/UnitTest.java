package com.namerek.core.Tests;

import com.namerek.core.BusinessLogic.Interfaces.IAddressService;
import com.namerek.core.BusinessLogic.Interfaces.IClientService;
import com.namerek.core.BusinessLogic.Interfaces.IOrderService;
import com.namerek.core.BusinessLogic.Services.AddressService;
import com.namerek.core.BusinessLogic.Services.ClientService;
import com.namerek.core.BusinessLogic.Services.OrderService;
import com.namerek.core.DTOs.*;
import com.namerek.core.Helpers.Utils.PackageProperties;
import org.mockito.Mock;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UnitTest {
    @Mock
    private IOrderService orderService;
    @Mock
    private IAddressService addressService;
    @Mock
    private IClientService clientService;

    @Test
    public final void addAddress(){
        addressService = new AddressService();
        var address = createOrder().getDeparturePoint();

        addressService.addAddress(address);

        var found = addressService.getAddresses().get(0);

        assert(found != null);
        assert (Objects.equals(found.getCountry(), address.getCountry()));
    }

    @Test
    public final void addOrderAndFindOrderInDB(){
        addressService = new AddressService();
        clientService = new ClientService();
        orderService = new OrderService();

        var order = createOrder();
        var departurePoint = order.getDeparturePoint();
        var destination = order.getDestination();
        var sender = order.getSender();
        var receiver = order.getReceiver();
        clientService.addClient(sender);
        clientService.addClient(receiver);
        addressService.addAddress(departurePoint);
        addressService.addAddress(destination);
        var trackNumber = orderService.addOrder(order, createPackages(), createPackageProperties());
        var found = orderService.getById(trackNumber);

        assert(found.getTrackNumber() == order.getTrackNumber());
        assert(found.getSender().getPhoneNumber() == sender.getPhoneNumber());
        assert(found.getReceiver().getPhoneNumber() == receiver.getPhoneNumber());
    }


    //TODO: edit order
    @Test
    public final void AddOrderFindAndUpdateOrder(){

    }
    //TODO: delete order
    @Test
    public final void DeleteOrder(){

    }
    //TODO: make tests that check price for order with current properties,
    @Test
    public final void checkPriceForOrderWithCurrentProperties(){

    }
    private OrderDTO createOrder(){
        AddressDTO firstAddress = new AddressDTO(1,
                "Ukraine",
                "Kyiv",
                "Evgene kharchenka",
                "43",
                "22222",
                true);
        AddressDTO secondAddress = new AddressDTO(2,
                "Ukraine",
                "Lviv",
                "Vokzalna",
                "23",
                "55555",
                true);
        PostOfficeDTO firstPO = new PostOfficeDTO(123, firstAddress, "Post");
        PostOfficeDTO secondPO = new PostOfficeDTO(321, secondAddress, "Post");
        return new OrderDTO(1,
                new ClientDTO(992930671,
                        "Niko",
                        "Tym",
                        "Individual",
                        0),
                new ClientDTO(992930672,
                        "Hib",
                        "Ali",
                        "Legal",
                        523464532),
                firstAddress,
                secondAddress,
                "Send.",
                123.3,
                new Date(),
                new CellDTO(1, "A3B2", firstPO));
    }

    private List<PackageDTO> createPackages(){
        List<PackageDTO> packages = new ArrayList<>();

        packages.add(new PackageDTO(1,
                5,
                null,
                "Medium weight, medium sized",
                null,
                "Accessories",
                null,
                500));

        return packages;
    }
    private List<PackageProperties> createPackageProperties(){
        List<PackageProperties> packageProperties = new ArrayList<>();

        packageProperties.add( new PackageProperties(55, 55 ,55));

        return packageProperties;
    }
}

package com.namerek.core.BusinessLogic.Interfaces;

import com.namerek.core.DTOs.LightOrderDTO;
import com.namerek.core.DTOs.OrderDTO;
import com.namerek.core.DTOs.PackageDTO;
import com.namerek.core.Helpers.Utils.PackageProperties;

import java.util.List;

public interface IOrderService {
    long addOrder(OrderDTO order, List<PackageDTO> packages, List<PackageProperties> packagePackageProperties);

    List<OrderDTO> getOrders();

    List<LightOrderDTO> getLightOrders();

    OrderDTO getById(long id);

    void editOrder(OrderDTO newOrder);

    void editPackages(List<PackageDTO> packages);

    void deleteOrder(OrderDTO order);

    List<PackageDTO> getPackages(long trackNumber);
}

package com.namerek.core.BusinessLogic.Interfaces;

import com.namerek.core.DTOs.AddressDTO;

import java.util.List;

public interface IAddressService {
    AddressDTO getById(long id);

    void addAddress(AddressDTO address);

    List<AddressDTO> getAddresses();

    void deleteAddress(AddressDTO address);
    AddressDTO checkIfAddressExist(AddressDTO address);
}

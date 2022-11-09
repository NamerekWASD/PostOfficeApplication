package com.namerek.core.BusinessLogic.Services;

import com.namerek.core.BusinessLogic.Interfaces.IAddressService;
import com.namerek.core.DTOs.AddressDTO;
import com.namerek.core.Entities.Address;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.UnitOfWorks.GenericRepository;
import com.namerek.core.UnitOfWorks.IGenericRepository;

import java.util.List;

public class AddressService implements IAddressService {
    private static final ModelMapperConverter<Address, AddressDTO> mapper =
            new ModelMapperConverter<>(Address.class, AddressDTO.class);

    private final IGenericRepository<Address, Long> addressRepository;

    public AddressService() {
        addressRepository = new GenericRepository<>(Address.class);
    }

    public AddressDTO getById(long id) {
        return mapper.toB(addressRepository.find(id));
    }

    public List<AddressDTO> getAddresses() {
        return mapper.toBList(addressRepository.findAll());
    }

    public void addAddress(AddressDTO address) {
            addressRepository.save(mapper.toA(address));
    }

    public void deleteAddress(AddressDTO address) {
        addressRepository.delete(mapper.toA(address));
    }

    public AddressDTO checkIfAddressExist(AddressDTO address) {

        for (var DBAddress :
                mapper.toBList(addressRepository.findAll())) {
            if (address.equals(DBAddress)) {
                return DBAddress;
            }
        }
        return address;
    }
}
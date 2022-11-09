package com.namerek.core.BusinessLogic.Services;

import com.namerek.core.BusinessLogic.Interfaces.IClientService;
import com.namerek.core.DTOs.ClientDTO;
import com.namerek.core.Entities.Client;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.UnitOfWorks.GenericRepository;
import com.namerek.core.UnitOfWorks.IGenericRepository;

public class ClientService implements IClientService {

    ModelMapperConverter<Client, ClientDTO> mapper;

    IGenericRepository<Client, Long> clientRepository;
    public  ClientService(){
        mapper = new ModelMapperConverter<>(Client.class, ClientDTO.class);
        clientRepository = new GenericRepository<>(Client.class);
    }

    @Override
    public ClientDTO getById(long id) {
        Client client = clientRepository.find(id);
        if (client == null) {
            return null;
        }
        return mapper.toB(client);
    }

    @Override
    public void addClient(ClientDTO sender) {
        if(getById(sender.getPhoneNumber()) == null)
            clientRepository.save(mapper.toA(sender));
    }


}

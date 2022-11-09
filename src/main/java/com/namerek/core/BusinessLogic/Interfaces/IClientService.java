package com.namerek.core.BusinessLogic.Interfaces;

import com.namerek.core.DTOs.ClientDTO;

public interface IClientService {
    ClientDTO getById(long id);

    void addClient(ClientDTO sender);
}

package com.namerek.core.BusinessLogic.Interfaces;

import com.namerek.core.DTOs.PostOfficeDTO;

import java.util.List;

public interface IPostOfficeService {
    void add(PostOfficeDTO postOffice);

    PostOfficeDTO getByNumber(long number);

    List<PostOfficeDTO> getPostOffices();

    PostOfficeDTO getByAddress(long id);
}

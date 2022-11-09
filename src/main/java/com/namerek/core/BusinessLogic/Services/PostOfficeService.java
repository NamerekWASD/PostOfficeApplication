package com.namerek.core.BusinessLogic.Services;

import com.namerek.core.BusinessLogic.Interfaces.IPostOfficeService;
import com.namerek.core.DTOs.PostOfficeDTO;
import com.namerek.core.Entities.PostOffice;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.UnitOfWorks.GenericRepository;
import com.namerek.core.UnitOfWorks.IGenericRepository;

import java.util.List;

public class PostOfficeService implements IPostOfficeService {
    private static final ModelMapperConverter<PostOffice, PostOfficeDTO> mapper =
            new ModelMapperConverter<>(PostOffice.class, PostOfficeDTO.class);

    private final IGenericRepository<PostOffice, Long> postOfficeRepository;

    public PostOfficeService (){
        postOfficeRepository = new GenericRepository<>(PostOffice.class);
    }

    @Override
    public void add(PostOfficeDTO postOffice){
        postOfficeRepository.save(PostOfficeService.mapper.toA(postOffice));
    }

    @Override
    public PostOfficeDTO getByNumber(long number){
        return PostOfficeService.mapper.toB(postOfficeRepository.find(number));
    }

    @Override
    public List<PostOfficeDTO> getPostOffices(){
        return PostOfficeService.mapper.toBList(postOfficeRepository.findAll());
    }

    @Override
    public PostOfficeDTO getByAddress(long addressId) {
        for(var postOffice:
        getPostOffices()){
            if(postOffice.getAddress().getId() == addressId){
                return postOffice;
            }
        }
        return null;
    }
}

package com.namerek.core.Mappers;


import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperConverter<A, B> {
    Class<A> Aclass;
    Class<B> Bclass;
    private final ModelMapper modelMapper;

    public ModelMapperConverter(Class<A> Aclass, Class<B> Bclass) {
        this.Aclass = Aclass;
        this.Bclass = Bclass;
        modelMapper = new ModelMapper();
    }

    public A toA(B sourceCode) {
        return modelMapper.map(sourceCode, Aclass);
    }

    public B toB(A sourceCode) {
        return modelMapper.map(sourceCode, Bclass);
    }

    public List<A> toAList(List<B> all) {
        ArrayList<A> result = new ArrayList<>();
        for (B sourceCode :
                all) {
            result.add(toA(sourceCode));
        }
        return result;
    }

    public List<B> toBList(List<A> all) {
        ArrayList<B> result = new ArrayList<>();
        for (A sourceCode :
                all) {
            result.add(toB(sourceCode));
        }
        return result;
    }
}

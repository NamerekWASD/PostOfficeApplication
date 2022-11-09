package com.namerek.core.UnitOfWorks;

import java.util.List;

public interface IGenericRepository<T, ID>{

    T find(ID id);
    List<T> findAll();
    T save(T object);
    T delete(T object);
    void close();
}

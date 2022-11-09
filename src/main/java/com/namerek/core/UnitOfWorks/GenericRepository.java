package com.namerek.core.UnitOfWorks;

import com.namerek.core.DataAccess.DBContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GenericRepository<T, ID> implements IGenericRepository<T, ID>{
    private final Class<T> tClass;
    private final DBContext context;
    public GenericRepository(Class<T> tClass){
        this.tClass = tClass;
        context = new DBContext();
        context.beginTransaction();
    }
    public GenericRepository(Class<T> tClass, DBContext dbContext){
        this.tClass = tClass;
        context = dbContext;
        context.beginTransaction();
    }

    @Override
    public T find(ID id) {
        return context.getEntityManager().find(tClass, id);
    }

    @Override
    public List<T> findAll() {
        List<T> objectList;
        try{
            objectList = context.getEntityManager()
                    .createQuery("SELECT a FROM " + tClass.getName() + " a").getResultList();
            return objectList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T save(T object) {
        Object fromDB = new Object();
        try {
            context.getEntityManager().persist(object);
            context.commitTransaction();
        }catch (Exception e){
            try{
                context.getEntityManager().merge(object);
                context.commitTransaction();
            }catch (Exception exception){
                e.printStackTrace();
            }
        }
        return object;
    }

    @Override
    @Transactional
    public T delete(T object) {
        context.beginTransaction();
        try {
            context.getEntityManager().persist(object);
            context.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public void close(){
        context.close();
    }
}

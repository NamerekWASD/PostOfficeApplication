package com.namerek.core.DataAccess;


import jakarta.persistence.EntityManager;

public class DBContext {
    private final EntityManager entityManager;

    public DBContext() {
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}

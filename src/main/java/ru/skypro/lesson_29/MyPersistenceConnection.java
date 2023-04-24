package ru.skypro.lesson_29;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyPersistenceConnection implements AutoCloseable{
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    public MyPersistenceConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        if(entityManagerFactory!=null && entityManager!=null) {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

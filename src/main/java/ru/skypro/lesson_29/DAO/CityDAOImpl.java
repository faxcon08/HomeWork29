package ru.skypro.lesson_29.DAO;

import ru.skypro.lesson_29.City;
import ru.skypro.lesson_29.MyPersistenceConnection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityDAOImpl implements CityDAO{

    @Override
    public int addCity(City city) {
        if (city.getCityId() == -1) {
            city.setCityId(this.getLastId()+1);
        }
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
        }
        return city.getCityId();
    }

    @Override
    public void deleteCity(City city) {
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(city));
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void updateCity(City city) {
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(city);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public Optional<City> getCityByID(int id) {
        Optional<City> ourCity = Optional.empty();
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();
            ourCity=Optional.ofNullable(entityManager.find(City.class, id));
            entityManager.getTransaction().commit();
        }
        return ourCity;
    }

    @Override
    public List<City> getAllCity() {
        List <City> list = new ArrayList<>();
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT c FROM City c");
            list = query.getResultList();
            entityManager.getTransaction().commit();
        }
        return list;
    }
    ///////////////////////////////////////

    @Override
    public void printAllCity() {
        List<City> cities = getAllCity();
        System.out.println("----------------------------");
        if (cities.isEmpty()) {
            System.out.println("CITIES is EMPTY");
            System.out.println("----------------------------");
            return;
        }
        for (City city : cities) {
            System.out.println(city);
        }
        System.out.println("----------------------------");
    }

    public int getLastId() {
        int id = 1;
        if(getAllCity().isEmpty())
            return id;
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT max(c.cityId) FROM City c");
            id = (Integer)query.getSingleResult();

            entityManager.getTransaction().commit();
        }

        return id;
    }
}

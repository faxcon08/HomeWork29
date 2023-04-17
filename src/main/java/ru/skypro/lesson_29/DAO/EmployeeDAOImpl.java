package ru.skypro.lesson_29.DAO;

import ru.skypro.lesson_29.Employee;
import ru.skypro.lesson_29.MyPersistenceConnection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public void addEmployee(Employee employee) {
        try (MyPersistenceConnection connection = new  MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(employee);

            entityManager.getTransaction().commit();
        }

    }

    @Override
    public Optional<Employee> getEmployeeByID(int id) {
        Optional<Employee> result=Optional.empty();
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            Employee foundEmploye = entityManager.find(Employee.class,id);
            result = Optional.ofNullable(foundEmploye);
            entityManager.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            Query query=entityManager.createQuery("SELECT e FROM Employee e");
            list=query.getResultList();
            entityManager.getTransaction().commit();
        }
        return list;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.merge(employee);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.remove(entityManager.merge(employee));
            entityManager.getTransaction().commit();
        }
    }

    public Integer getIDbyEmployee(Employee employee) {
        Integer id = null;
        try (MyPersistenceConnection connection = new MyPersistenceConnection()) {
            EntityManager entityManager = connection.getEntityManager();
            entityManager.getTransaction().begin();

            Query query=entityManager.createQuery("SELECT e.id FROM Employee e WHERE e.firstName=:firstName AND e.lastName=:lastName AND e.age=:age AND e.gender=:gender");
            query.setParameter("firstName",employee.getFirstName());
            query.setParameter("lastName", employee.getLastName());
            query.setParameter("age", employee.getAge());
            query.setParameter("gender", employee.getGender());

            id = (Integer)query.getSingleResult();

            entityManager.getTransaction().commit();
        }
        return id;
    }
}

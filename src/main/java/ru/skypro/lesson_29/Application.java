package ru.skypro.lesson_29;

import ru.skypro.lesson_29.DAO.CityDAO;
import ru.skypro.lesson_29.DAO.CityDAOImpl;
import ru.skypro.lesson_29.DAO.EmployeeDAO;
import ru.skypro.lesson_29.DAO.EmployeeDAOImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void mySout(String str) {
        System.out.println(ANSI_GREEN+str+ANSI_RESET);
    }

    public static void main(String[] args) {
        mySout("-------- First Task ---------");

        City city = new City("Архангельск",new ArrayList<Employee>());
        Employee employee1 = new Employee("TypicalFirstName1", "TypicalLastName1", 18, "male", city);
        Employee employee2 = new Employee("TypicalFirstName2", "TypicalLastName2", 19, "male", city);
        Employee employee3 = new Employee("TypicalFirstName3", "TypicalLastName3", 20, "male", city);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        city.setEmployees(employees);
        CityDAO cityDAO = new CityDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        cityDAO.addCity(city);
        mySout("Добавили Город: "+city);
        mySout("И 3х сотрудников: \n"+city.getEmployees().get(0)+"\n"+city.getEmployees().get(1)+"\n"+city.getEmployees().get(2));
        cityDAO.printAllCity();
        employeeDAO.printAllEmployees();


        Employee updatedEmployee1 = new Employee(employee1.getId(),"UpdatedFistName1","UpdatedLastName1", 21, "male",city);
        city.getEmployees().set(0,updatedEmployee1);
        cityDAO.updateCity(city);
        mySout("[Замелини сотрудника:] "+employee1 + "\n[на сотрудника:] "+ updatedEmployee1);
        employeeDAO.printAllEmployees();

        mySout("После удаления города: "+city);
        cityDAO.deleteCity(city);
        cityDAO.printAllCity();
        employeeDAO.printAllEmployees();
    }// main
}// Aplication

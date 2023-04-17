package ru.skypro.lesson_29.DAO;

import ru.skypro.lesson_29.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    public void addEmployee(Employee employee);

    public Optional<Employee> getEmployeeByID(int id);

    public List<Employee> getAllEmployees();
    public void updateEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

    ///////////////
    default public void printAllEmployees() {
        List<Employee> list = this.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("Employees is EMPTY");
            return;
        }
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
    public Integer getIDbyEmployee(Employee employee);
}

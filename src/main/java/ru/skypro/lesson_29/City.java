package ru.skypro.lesson_29;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="City")
public class City {
    public City() {}
    public City(int id, String cityNameб, List<Employee> employees) {
        this.cityId=id;
        this.cityName=cityName;
        this.employees=employees;
    }
    public City(String cityName,List<Employee> employees) {
        this.cityId=-1;
        this.cityName=cityName;
        this.employees=employees;
    }
    @Id
    @Column(name = "city_id", nullable = false, unique = true)
    private int cityId;
    @Column(name = "city_name",length = 60, nullable = false)
    private String cityName;

    // FetchType.LAZY количество записей небольшое и можно не боятся проблемы n+1;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Employee> employees;

    //////////////////// getters & setters ///////////////

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    ////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "City: #"+cityId+" - "+cityName;
    }
}

package ru.skypro.lesson_29;

import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {
    public Employee() {

    }

    public Employee(int id, String firstName, String lastName, int age, String gender, City city) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.gender=gender;
        this.city=city;
    }
    public Employee(String firstName, String lastName, int age, String gender, City city) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.gender=gender;
        this.city=city;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Column(name = "gender", length = 6, nullable = false)
    private String gender;
    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    /////////////////////// getters ///////////////////

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public City getCity() {
        return city;
    }

    ////////////////////////////// setters //////////////////

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(City city) {
        this.city = city;
    }

    //////////////////////////////////////

    @Override
    public String toString() {
        return "ID("+id+") "+firstName+" "+lastName+" ["+age+"]"+"{"+gender+"}"+"  City = "+city.getCityName();
    }
}

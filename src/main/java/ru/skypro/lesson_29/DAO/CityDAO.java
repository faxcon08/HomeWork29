package ru.skypro.lesson_29.DAO;

import ru.skypro.lesson_29.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO {
    public int addCity(City city);
    public void deleteCity(City city);
    public void updateCity(City city);
    public Optional<City> getCityByID(int id);
    public List<City> getAllCity();

    //////////////////////////
    public void printAllCity();
    public int getLastId();

}

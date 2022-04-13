package com.user.service.services;

import com.user.service.entities.User;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public List<User> getAll();

    public User getUserById(Integer id);

    public User save(User user);

    //restTemplate--------------

    public List<Car> getCars(Integer userId);

    public  List<Motorcycle> getMotorcycles(Integer userId);

    //feign Client--------------

    public Car saveCar(Integer userId , Car car);

    public Motorcycle saveMotorcycle(Integer userId, Motorcycle motorcycle);

    public Map<String, Object> getUserAndVehiclesById(Integer userId);
}

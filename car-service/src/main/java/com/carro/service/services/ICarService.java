package com.carro.service.services;

import com.carro.service.entities.Car;

import java.util.List;

public interface ICarService {

    public List<Car> getAll();


    public Car getCarById(Integer id);

    public Car save(Car user);

    public List<Car> byUserId(Integer userId);
}

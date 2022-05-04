package com.carro.service.services.impl;


import com.carro.service.entities.Car;
import com.carro.service.repositories.CarRepository;
import com.carro.service.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public List<Car> getAll(){
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id){
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car save(Car user){
        Car userSave = carRepository.save(user);
        return userSave;
    }

    public List<Car> byUserId(Integer userId){
        return carRepository.findByUserId(userId);
    }
}

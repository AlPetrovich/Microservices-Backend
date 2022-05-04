package com.carro.service.controllers;

import com.carro.service.entities.Car;
import com.carro.service.services.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/list")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Integer id){
        Car car = carService.getCarById(id);
        if (car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping()
    public  ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car saveCar = carService.save(car);
        return ResponseEntity.ok(saveCar);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> listCarByUserId(@PathVariable("userId") Integer id){
        List<Car> cars = carService.byUserId(id);
        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}

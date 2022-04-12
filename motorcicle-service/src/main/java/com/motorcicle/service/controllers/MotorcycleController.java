package com.motorcicle.service.controllers;
import com.motorcicle.service.entities.Motorcycle;
import com.motorcicle.service.services.impl.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping("/list")
    public ResponseEntity<List<Motorcycle>> getAllCars(){
        List<Motorcycle> cars = motorcycleService.getAll();
        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycle(@PathVariable("id") Integer id){
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);
        if (motorcycle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycle);
    }

    @PostMapping()
    public  ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle motorcycle){
        Motorcycle saveMotorcycle = motorcycleService.save(motorcycle);
        return ResponseEntity.ok(saveMotorcycle);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Motorcycle>> listMotorcycleById(@PathVariable("userId") Integer id){
        List<Motorcycle> motorcycle = motorcycleService.byUserId(id);
        if (motorcycle.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycle);
    }



}

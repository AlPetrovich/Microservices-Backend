package com.motorcicle.service.services.impl;
import com.motorcicle.service.entities.Motorcycle;
import com.motorcicle.service.repositories.MotorcycleRepository;
import com.motorcicle.service.services.IMotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotorcycleService implements IMotorcycleService{

    @Autowired
    private MotorcycleRepository motorcycleService;

    @Override
    public List<Motorcycle> getAll(){
        return motorcycleService.findAll();
    }

    @Override
    public Motorcycle getMotorcycleById(Integer id){
        return motorcycleService.findById(id).orElse(null);
    }

    @Override
    public Motorcycle save(Motorcycle motorcicle){
        Motorcycle motorcicleSave = motorcycleService.save(motorcicle);
        return motorcicleSave;
    }

    public List<Motorcycle> byUserId(Integer userId){
        return motorcycleService.findByUserId(userId);
    }
}

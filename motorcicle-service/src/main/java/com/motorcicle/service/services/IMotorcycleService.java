package com.motorcicle.service.services;

import com.motorcicle.service.entities.Motorcycle;

import java.util.List;

public interface IMotorcycleService {


    public List<Motorcycle> getAll();

    public Motorcycle getMotorcycleById(Integer id);

    public Motorcycle save(Motorcycle motorcycle);
}

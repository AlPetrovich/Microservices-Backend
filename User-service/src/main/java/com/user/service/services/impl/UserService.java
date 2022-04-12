package com.user.service.services.impl;
import com.user.service.entities.User;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.repositories.UserRepository;
import com.user.service.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    //restTemplane va a la api de carro y me obtiene el carro de un usuario en particular
    public List<Car> getCars(Integer userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8082/api/cars/user/" + userId, List.class);
        return cars;
    }

    //api de motocicletas
    public  List<Motorcycle> getMotorcycles(Integer userId){
        List<Motorcycle> motorcycles = restTemplate.getForObject("http://localhost:8083/api/motorcycles/user/" + userId, List.class);
        return motorcycles;
    }

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user){
        User userSave = userRepository.save(user);
        return userSave;
    }

}

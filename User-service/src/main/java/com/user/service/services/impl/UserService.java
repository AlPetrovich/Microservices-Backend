package com.user.service.services.impl;
import com.user.service.entities.User;
import com.user.service.feingclients.CarFeignClient;
import com.user.service.feingclients.MotorcycleFeignClient;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.repositories.UserRepository;
import com.user.service.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    //Inyeccion de Feign
    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private MotorcycleFeignClient motorcycleFeignClient;

    //-------------------------RestTemplate---------------------------
    //restTemplane va a la api de carro y me obtiene el carro de un usuario en particular
    @Override
    public List<Car> getCars(Integer userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8082/api/cars/user/" + userId, List.class);
        return cars;
    }

    //api de motocicletas
    @Override
    public  List<Motorcycle> getMotorcycles(Integer userId){
        List<Motorcycle> motorcycles = restTemplate.getForObject("http://localhost:8083/api/motorcycles/user/" + userId, List.class);
        return motorcycles;
    }

    // ------------- Cliente feign -----------------------

    @Override
    public Car saveCar(Integer userId ,Car car){
        car.setUserId(userId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }

    @Override
    public Motorcycle saveMotorcycle(Integer userId, Motorcycle motorcycle){
        motorcycle.setUserId(userId);
        Motorcycle newMoto = motorcycleFeignClient.save(motorcycle);
        return newMoto;
    }

    @Override
    public Map<String, Object> getUserAndVehiclesById(Integer userId){
        Map<String, Object> result = new HashMap<>();
        //Validar usuario
        User user = userRepository.findById(userId).orElse(null);
        if( user == null){
            result.put("Message", "User does not exist");
        }
        result.put("Usuario",user);
        //Obtener lista de autos del usuario
        List<Car> carList = carFeignClient.getCarsByUserId(userId);
        if (carList.isEmpty()){
            result.put("Cars","User does not have a car");
        }else{
            result.put("Cars",carList);
        }
        //Obtener lista de motos del usuario
        List<Motorcycle> motorcycleList = motorcycleFeignClient.getMotorcyclesByUserId(userId);
        if (carList.isEmpty()){
            result.put("Motorcycles","User does not have a motorcycle");
        }else{
            result.put("Motorcycles",motorcycleList);
        }
        return result;
    }


    //-------------------------------------------------------

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

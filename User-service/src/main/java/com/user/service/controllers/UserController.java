package com.user.service.controllers;
import com.user.service.entities.User;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public  ResponseEntity<User> saveUser(@RequestBody User user){
        User saveUser = userService.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> listCars(@PathVariable("userId") Integer userId){
        //obtenemos el usuario
        User user = userService.getUserById(userId);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        //obtenemos los autos del usuario
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/motorcycles/{userId}")
    public ResponseEntity<List<Motorcycle>> listMotorcycle(@PathVariable("userId") Integer userId){
        //obtenemos el usuario
        User user = userService.getUserById(userId);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        //obtenemos los autos del usuario
        List<Motorcycle> motorcycles = userService.getMotorcycles(userId);
        return ResponseEntity.ok(motorcycles);
    }

}

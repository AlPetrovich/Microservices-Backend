package com.user.service.feingclients;
import com.user.service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//server.port=8082
//spring.application.name=car-service
//cliente feign, nombre del microservicio y su url para acceder a sus metodos
@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping()
    public Car save(@RequestBody Car car);

    @GetMapping("/user/{userId}")
    public List<Car> getCarsByUserId(@PathVariable("userId") Integer userId);

}

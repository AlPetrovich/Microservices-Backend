package com.user.service.feingclients;
import com.user.service.models.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "motorcycle-service", url = "http://localhost:8083/api/motorcycles")
public interface MotorcycleFeignClient {

    @PostMapping()
    public Motorcycle save(@RequestBody Motorcycle motorcycle);

    @GetMapping("/user/{userId}")
    public List<Motorcycle> getMotorcyclesByUserId(@PathVariable("userId") Integer userId);
}

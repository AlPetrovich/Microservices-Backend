package com.user.service.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Car {

    private String brand;
    private String model;
    //trabajo con feign
    private Integer userId;

}

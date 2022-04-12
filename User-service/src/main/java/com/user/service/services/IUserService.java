package com.user.service.services;

import com.user.service.entities.User;

import java.util.List;

public interface IUserService {

    public List<User> getAll();

    public User getUserById(Integer id);

    public User save(User user);
}

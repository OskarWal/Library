package com.example.demo.services;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User getUser(String username);
    void saveUser(User user);
    public List<Authority> getAuthoritity(String username);

    List<User> getAllUsers();
}

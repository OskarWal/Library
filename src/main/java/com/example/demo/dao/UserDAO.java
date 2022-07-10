package com.example.demo.dao;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

import java.util.List;


public interface UserDAO {
    void saveUser(User user);
    User getUser(String username);
    List<Authority> getAuthoritity(String username);

    List<User> getAllUsers();
}

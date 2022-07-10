package com.example.demo.dao;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

import java.util.List;


public interface UserDAO {
    public void saveUser(User user);
    public User getUser(String username);
    public List<Authority> getAuthoritity(String username);

    List<User> getAllUsers();
}

package com.example.demo.services;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(pwd));
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<Authority> getAuthoritity(String username) {
        return userDAO.getAuthority(username);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}

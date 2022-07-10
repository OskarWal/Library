package com.example.demo.dao;

import com.example.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveUser(User user)
    {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }

    @Override
    public User getUser(String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        User user =  (User) currentSession.get(User.class, username);


        return user;
    }

    @Override
    public List<Authority> getAuthoritity(String username) {

        Session currentSession = sessionFactory.getCurrentSession();
        User user =  (User) currentSession.get(User.class, username);

        return user.getAuthorities();
    }

    @Override
    public List<User> getAllUsers()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        List<User> users =  currentSession.createQuery("from User", User.class).getResultList();

        return users;

    }
}

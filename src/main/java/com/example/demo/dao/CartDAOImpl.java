package com.example.demo.dao;

import com.example.demo.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Cart> getCart(String username) {
        Session currentSession = sessionFactory.getCurrentSession();

        List<Cart> cart = currentSession.createQuery("from Cart where user_id = :username").setParameter("username",username).list();
        return cart;
    }

    @Override
    public void saveCart(Cart cart) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(cart);

    }

    @Override
    public void deleteCartItem(Cart cart)
    {
        Session session = sessionFactory.getCurrentSession();

        session.delete(cart);
    }
}

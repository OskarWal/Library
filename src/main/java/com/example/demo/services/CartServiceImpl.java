package com.example.demo.services;

import com.example.demo.dao.CartDAO;
import com.example.demo.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDAO cartDAO;

    @Override
    @Transactional
    public List<Cart> getCart(String username) {
        return cartDAO.getCart(username);
    }

    @Override
    @Transactional
    public void saveCart(Cart cart) {
        cartDAO.saveCart(cart);
    }

    @Override
    @Transactional
    public void deleteCartItem(Cart cart) {
        cartDAO.deleteCartItem(cart);
    }
}

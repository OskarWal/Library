package com.example.demo.services;

import com.example.demo.entity.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getCart(String username);
    public void saveCart(Cart cart);
    public void deleteCartItem(Cart cart);
}

package com.example.demo.services;

import com.example.demo.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCart(String username);
    void saveCart(Cart cart);
    void deleteCartItem(Cart cart);
}

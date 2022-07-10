package com.example.demo.dao;


import com.example.demo.entity.Cart;

import java.util.List;

public interface CartDAO {
    List<Cart> getCart(String username);
    void saveCart(Cart cart);

    void deleteCartItem(Cart cart);
}

package com.example.demo.services;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllUserOrders(String username);
    List<Order> getAllOrders();
    Order getOrderById(int id);
    void saveOrder(Order order);
}

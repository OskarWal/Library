package com.example.demo.services;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllUserOrders(String username);
    public List<Order> getAllOrders();
    public Order getOrderById(int id);
    public void saveOrder(Order order);
}

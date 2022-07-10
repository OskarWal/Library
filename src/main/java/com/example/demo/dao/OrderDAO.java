package com.example.demo.dao;

import com.example.demo.entity.Order;


import java.util.List;

public interface OrderDAO {
    public List<Order> getAllUserOrders(String username);
    public Order getOrderById(int id);
    public void saveOrder(Order order);

    List<Order> getAllOrders();
}

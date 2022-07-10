package com.example.demo.dao;

import com.example.demo.entity.Order;


import java.util.List;

public interface OrderDAO {
    List<Order> getAllUserOrders(String username);
    Order getOrderById(int id);
    void saveOrder(Order order);

    List<Order> getAllOrders();
}

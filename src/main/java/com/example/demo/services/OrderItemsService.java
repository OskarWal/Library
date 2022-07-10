package com.example.demo.services;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;

import java.util.List;

public interface OrderItemsService {
    public List<OrderItems> getAllItemsOrder(Order order);
    public void saveOrderItems(OrderItems orderItems);
}

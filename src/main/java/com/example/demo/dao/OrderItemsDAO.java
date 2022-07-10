package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;

import java.util.List;

public interface OrderItemsDAO
{
    void saveOrderItems(OrderItems orderItems);

    List<OrderItems> getAllItemsOrder(Order order);
}

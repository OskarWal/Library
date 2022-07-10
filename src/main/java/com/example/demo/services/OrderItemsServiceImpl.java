package com.example.demo.services;

import com.example.demo.dao.OrderItemsDAO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{

    @Qualifier("orderItemsDAOImpl")
    @Autowired
    private OrderItemsDAO orderItemsDAO;



    @Override
    @Transactional
    public List<OrderItems> getAllItemsOrder(Order order) {
        return orderItemsDAO.getAllItemsOrder(order);
    }

    @Override
    @Transactional
    public void saveOrderItems(OrderItems orderItems) {
        orderItemsDAO.saveOrderItems(orderItems);
    }
}

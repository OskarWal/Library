package com.example.demo.services;

import com.example.demo.dao.OrderDAO;
import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Qualifier("orderDAOImpl")
    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional
    public List<Order> getAllUserOrders(String username) {
        return orderDAO.getAllUserOrders(username);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }
}

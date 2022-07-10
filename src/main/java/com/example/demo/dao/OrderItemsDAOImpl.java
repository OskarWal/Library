package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemsDAOImpl implements OrderItemsDAO
{
    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public void saveOrderItems(OrderItems orderItems) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(orderItems);
    }

    @Override
    public List<OrderItems> getAllItemsOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        List<OrderItems> orderItems = session.createQuery("from OrderItems where order = :order").setParameter("order",order).list();

        return orderItems;
    }
}

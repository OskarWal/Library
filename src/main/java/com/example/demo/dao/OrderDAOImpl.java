package com.example.demo.dao;

import com.example.demo.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getAllUserOrders(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Order> orders = currentSession.createQuery("from Order ord where username = :username order by ord.id").setParameter("username",username).list();
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Order order = (Order) currentSession.get(Order.class, id);
        return order;
    }

    @Override
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(order);
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> orders = session.createQuery("from Order ord order by ord.id", Order.class).getResultList();
        return orders;
    }
}

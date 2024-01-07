package com.example.demo.dao;

import com.example.demo.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Category> query = currentSession.createQuery("from Category", Category.class);
        List<Category> categories = query.getResultList();
        return categories ;
    }


    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(category);
    }

    @Override
    public Category getCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();

        Category category = session.get(Category.class,categoryId);

        return category;
    }

    @Override
    public void deleteCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }
}

package com.example.demo.dao;

import com.example.demo.entity.Kategoria;
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
    public List<Kategoria> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Kategoria> query = currentSession.createQuery("from Kategoria", Kategoria.class);
        List<Kategoria> categories = query.getResultList();
        return categories ;
    }


    @Override
    public void saveCategory(Kategoria kategoria) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(kategoria);
    }

    @Override
    public Kategoria getCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();

        Kategoria category = session.get(Kategoria.class,categoryId);

        return category;
    }

    @Override
    public void deleteCategory(Kategoria category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }
}

package com.example.demo.dao;


import com.example.demo.entity.Book;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Book> getBooks() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> query = currentSession.createQuery("from Book book order by book.id desc", Book.class);
        List<Book> books = query.getResultList();


        return books ;
    }
    @Override
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(book);
    }

    @Override
    public Book getBookById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Book book =  (Book) currentSession.get(Book.class, id);

        return book ;
    }

    @Override
    public void deleteBook(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.delete(book);

    }
}

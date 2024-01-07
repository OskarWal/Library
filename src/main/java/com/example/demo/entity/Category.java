package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",cascade = CascadeType.ALL)
    List<Book> books;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
//    public void addBook(Book book){
//        if (books.isEmpty())
//            books = new ArrayList<>();
//        book.setCategory(this);
//        books.add(book);
//    }
    @Override
    public String toString() {
        return "Kategoria{" +
                "id=" + id +
                ", nazwa='" + name + '\'' +
                '}';
    }
}
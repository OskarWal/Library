package com.example.demo.entity;

import javax.persistence.*;

@Entity
@IdClass(CartID.class)
@Table(name = "carts")
public class Cart
{
    @Id
    private String user_id;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "books_id", nullable = false)
    private Book books_id;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Book getBookId() {
        return books_id;
    }

//    public void setBooks_id(Book books_id) {
//        this.books_id = books_id;
//    }

    public Cart(String username, Book books_id, int quantity)
    {
        this.user_id = username;
        this.books_id = books_id;
        this.quantity = quantity;
    }

    public Cart()
    {
    }

    public String getUsername() {
        return user_id;
    }

    public void setUsername(String username) {
        this.user_id = username;
    }

    //public Book getBookId() {
    //    return books_id;
    //}

    @Override
    public String toString() {
        return "Cart{" +
                "username='" + user_id + '\'' +
                ", ksiazka_id=" + books_id +
                ", quantity=" + quantity +
                '}';
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

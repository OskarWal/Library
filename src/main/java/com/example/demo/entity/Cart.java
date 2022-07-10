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
    @JoinColumn(name = "ksiazki_id", nullable = false)
    private Ksiazka ksiazki_id;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Ksiazka getKsiazki_id() {
        return ksiazki_id;
    }

    public void setKsiazki_id(Ksiazka ksiazki_id) {
        this.ksiazki_id = ksiazki_id;
    }

    public Cart(String username, Ksiazka ksiazki_id, int quantity)
    {
        this.user_id = username;
        this.ksiazki_id = ksiazki_id;
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

    public Ksiazka getKsiazka_id() {
        return ksiazki_id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "username='" + user_id + '\'' +
                ", ksiazka_id=" + ksiazki_id +
                ", quantity=" + quantity +
                '}';
    }

    public void setKsiazka_id(Ksiazka ksiazka_id) {
        this.ksiazki_id = ksiazka_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

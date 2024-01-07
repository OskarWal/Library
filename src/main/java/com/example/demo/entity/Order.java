package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private String status;

    @Column(name = "username")
    private String username;

    public Order(String status, String username) {
        this.status = status;
        this.username = username;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

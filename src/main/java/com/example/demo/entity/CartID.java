package com.example.demo.entity;

import java.io.Serializable;

public class CartID implements Serializable {
    private String user_id;

    private int ksiazki_id;

    public CartID(String user_id, int ksiazki_id) {
        this.user_id = user_id;
        this.ksiazki_id = ksiazki_id;
    }

    public CartID() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getKsiazki_id() {
        return ksiazki_id;
    }

    public void setKsiazki_id(int ksiazki_id) {
        this.ksiazki_id = ksiazki_id;
    }
}
package com.example.demo.entity;

import java.io.Serializable;

public class CartID implements Serializable {
    private String user_id;

    private int books_id;

    public CartID(String user_id, int books_id) {
        this.user_id = user_id;
        this.books_id = books_id;
    }

    public CartID() {
    }
}

//    public String getUserId() {
//        return user_id;
//    }

//    public void setUserId(String user_id) {
//        this.user_id = user_id;
//    }

//    public int getBooks_id() {
//        return books_id;
//    }

//    public void setBooks_id(int ksiazki_id) {
//        this.books_id = ksiazki_id;
//    }
//}
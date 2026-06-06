package com.salimocarvalho.model;

import com.salimocarvalho.enums.TransactionType;

public class Category  {
    private int id;
    private String name;
    private TransactionType type;
    private  User user;

    public Category(int id, String name, TransactionType type, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
    }

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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.salimocarvalho.model;

import com.salimocarvalho.enums.TransactionType;

import java.time.LocalDate;

public class Expenses extends Transaction{


    public Expenses(int id, LocalDate date, String description, double value, TransactionType type, Category categoryId, User userId) {
        super(id, date, description, value, type, categoryId, userId);
    }
}

package com.salimocarvalho.model;

import com.salimocarvalho.enums.TransactionType;

import java.time.LocalDate;

public class Income extends Transaction {


    public Income(int id, LocalDate date, String description, double value, TransactionType type, Category categoryId, User userId) {
        super(id, date, description, value, type, categoryId, userId);
    }
}

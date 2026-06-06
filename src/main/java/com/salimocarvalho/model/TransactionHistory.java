package com.salimocarvalho.model;

import com.salimocarvalho.enums.HistoryType;
import com.salimocarvalho.enums.TransactionType;

import java.time.LocalDate;

public class TransactionHistory {
    private int id;
    private LocalDate date;
    private HistoryType type;
    private String Description;
    private double value;
    private Transaction transactionId;
    private TransactionType transactionType;
    private User userId ;


    public TransactionHistory(int id, LocalDate date, HistoryType type, String description, double value, Transaction transactionId, TransactionType transactionType, User userId) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.Description = description;
        this.value = value;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public HistoryType getType() {
        return type;
    }

    public void setType(HistoryType type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }



    public void setDescription(String description) {
        Description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}

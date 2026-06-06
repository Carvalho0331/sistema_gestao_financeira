package com.salimocarvalho.model;

import com.salimocarvalho.enums.TransactionType;

import java.time.LocalDate;
import java.util.Objects;

public  class Transaction {
    private  int id;
    private  LocalDate date;
    private  String Description;
    private  double value;
    private TransactionType type;
    private  Category CategoryId;
    private  User userId ;


    public Transaction(int id, LocalDate date, String description, double value, TransactionType type, Category categoryId, User userId) {
        this.id = id;
        this.date = date;
        Description = description;
        this.value = value;
        this.type = type;
        CategoryId = categoryId;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Category categoryId) {
        CategoryId = categoryId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", Description='" + Description + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", CategoryId=" + CategoryId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(value, that.value) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(Description, that.Description) &&
                type == that.type &&
                Objects.equals(CategoryId, that.CategoryId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, Description, value, type, CategoryId, userId);
    }
}

package com.salimocarvalho.dao;

import com.salimocarvalho.model.Transaction;

import java.util.List;

public interface TransactionDAO {
void createTransaction(Transaction transaction);
void updateTransaction(Transaction transaction);
void deleteTransaction(int id);
List<Transaction> showTransaction();
Transaction getTransaction(int id);
}

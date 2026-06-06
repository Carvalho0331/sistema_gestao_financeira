package com.salimocarvalho.dao;

import com.salimocarvalho.model.TransactionHistory;

import java.util.List;

public interface TransactionHistoryDAO {
void createTransactionHistory(TransactionHistory transactionHistory);
List<TransactionHistory> showTransactionHistory();
void updateTransactionHistory(TransactionHistory transactionHistory);
TransactionHistory getTransactionHistory(int id);
void deleteTransactionHistory(int id);
}

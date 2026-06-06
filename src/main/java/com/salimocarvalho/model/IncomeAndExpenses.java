package com.salimocarvalho.model;

import java.util.ArrayList;
import java.util.List;

public class IncomeAndExpenses implements Transactions {
    private List<Transaction> transactionsIncomeAndExpenses = new ArrayList<>();
    @Override
    public void registar(Transaction transaction) {
            transactionsIncomeAndExpenses.add(transaction);
    }
}

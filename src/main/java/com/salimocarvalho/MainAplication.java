package com.salimocarvalho;

import com.salimocarvalho.dao.*;
import com.salimocarvalho.enums.HistoryType;
import com.salimocarvalho.enums.Profile;
import com.salimocarvalho.enums.TransactionType;
import com.salimocarvalho.model.Category;
import com.salimocarvalho.model.Transaction;
import com.salimocarvalho.model.TransactionHistory;
import com.salimocarvalho.model.User;

import java.time.LocalDate;

public class MainAplication {

    public static void main(String[] args) {






        UserDAO userDAO = new UserDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        TransactionHistoryDAO transactionHistoryDAO = new TransactionHistoryImpl();



        for(Transaction transaction: transactionDAO.showTransaction()){
            transactionDAO.deleteTransaction(transaction.getId());
        }

        for(Category category: categoryDAO.showAllCategories()){
            categoryDAO.deleteCategory(category.getId());
        }



        for (User user: userDAO.showAllUsers()){
            userDAO.deleteUser(user.getId());
        }





        User user = new User(0,"Salimo Carvalho", "salimocarvalhos@gmail.com","12345",Profile.ADMIN);
        userDAO.createUser(user);
        Category category = new Category(0,"Internet",TransactionType.EXPENSE,user);
        categoryDAO.createCategory(category);

        String description = category.getName();
        LocalDate date = LocalDate.now();


        Transaction transaction = new Transaction(0,date,description,2000,TransactionType.EXPENSE,category,user);
        transactionDAO.createTransaction(transaction);
        User user1 = new User(0,"Joao Henrique", "joaoherinque@gmail.com","12345",Profile.ASSISTENTE);
        User user2 = new User(0,"Pedro Salomao", "pedrosalomao@gmail.com","12345",Profile.ASSISTENTE);
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        Category category1 = new Category(0,"Transporte",TransactionType.EXPENSE,user);
        Category category2 = new Category(0,"Salario",TransactionType.INCOME,user);
        categoryDAO.createCategory(category1);
        categoryDAO.createCategory(category2);

        Transaction transaction1 = new Transaction(0,date,description,8000,TransactionType.EXPENSE,category1,user);
        Transaction transaction2 = new Transaction(0,date,"Transporte",8000,TransactionType.EXPENSE,category2,user);
        transactionDAO.createTransaction(transaction1);
        transactionDAO.createTransaction(transaction2);

        category1.setName("Alimentacao");
        categoryDAO.updateCategory(category1);


        user1.setName("Alfredo Rafael");
        user1.setPassword("rodrigos2002");
        user1.setEmail("alfredorafael@gmial.com");
        userDAO.updateUser(user1);


        TransactionHistory transactionHistory = new TransactionHistory(
                0,
                date,
                HistoryType.CREATE,
                description,
                2000,
                transaction1,
                TransactionType.INCOME,
                user1);
        transactionHistoryDAO.createTransactionHistory(transactionHistory);


    }
}
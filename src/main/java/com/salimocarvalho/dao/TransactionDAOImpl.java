package com.salimocarvalho.dao;

import com.salimocarvalho.db.ConnectionDB;
import com.salimocarvalho.enums.TransactionType;
import com.salimocarvalho.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (value, date,description,type,category_id,user_id) VALUES (?,?,?,?,?,?) RETURNING id;";
    try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
        ps.setDouble(1, transaction.getValue());
        ps.setDate(2, java.sql.Date.valueOf(transaction.getDate()));
        ps.setString(3, transaction.getDescription());
        ps.setString(4, transaction.getType().name());
        ps.setInt(5, transaction.getCategoryId().getId());
        ps.setInt(6, transaction.getUserId().getId());

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            transaction.setId(rs.getInt("id"));
        }
        System.out.println("Transaction created");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET value = ?,date = ?,description = ?, type = ?,category_id, user_id = ? WHERE id = ?";

        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDouble(1, transaction.getValue());
            ps.setDate(2, java.sql.Date.valueOf(transaction.getDate()));
            ps.setString(3, transaction.getDescription());
            ps.setString(4, transaction.getType().name());
            ps.setInt(5, transaction.getCategoryId().getId());
            ps.setInt(6, transaction.getUserId().getId());
            ps.setInt(7, transaction.getId());
            ps.executeUpdate();
            System.out.println("Transaction updated");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTransaction(int id) {
        String sql = "DELETE FROM transaction WHERE id = ?";

        try(Connection con =ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Transaction deleted");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> showTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction;";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int userID = rs.getInt("user_id");
                int categoryID = rs.getInt("category_id");


                User user = new UserDAOImpl().getUser(userID);
                Category category  = new CategoryDAOImpl().getCategory(categoryID);
                LocalDate date = rs.getDate("date").toLocalDate();
                TransactionType type = TransactionType.valueOf(rs.getString("type"));

                Transaction t;

                if(type == TransactionType.INCOME){
                    t = new Income(rs.getInt("id"),date,rs.getString("description"),rs.getDouble("value"),type,category,user);
                }else{
                    t = new Expenses(rs.getInt("id"),date,rs.getString("description"),rs.getDouble("value"),type,category,user);
                }

                transactions.add(t);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public Transaction getTransaction(int id) {
        String sql = "SELECT * FROM transaction WHERE id = ?";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("user_id");
                int categoryID = rs.getInt("category_id");
                User user = new UserDAOImpl().getUser(userID);
                Category category  = new CategoryDAOImpl().getCategory(categoryID);
                LocalDate date = rs.getDate("date").toLocalDate();
                TransactionType type = TransactionType.valueOf(rs.getString("type"));
                Transaction t;
                if(type == TransactionType.INCOME){
                    t = new Income(rs.getInt("id"),date,rs.getString("description"),rs.getDouble("value"),type,category,user);
                }else{
                    t = new Expenses(rs.getInt("id"),date,rs.getString("description"),rs.getDouble("value"),type,category,user);
                }
                return t;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
}

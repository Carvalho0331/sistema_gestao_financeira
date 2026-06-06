package com.salimocarvalho.dao;

import com.salimocarvalho.db.ConnectionDB;
import com.salimocarvalho.enums.HistoryType;
import com.salimocarvalho.enums.TransactionType;
import com.salimocarvalho.model.Transaction;
import com.salimocarvalho.model.TransactionHistory;
import com.salimocarvalho.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryImpl implements TransactionHistoryDAO {

    @Override
    public void createTransactionHistory(TransactionHistory transactionHistory) {
        String sql = "INSERT INTO transaction_history (date,type,description,value,transaction_id,transaction_type,user_id) VALUES (?,?,?,?,?,?,?)";
    try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){

        ps.setDate(1, java.sql.Date.valueOf(transactionHistory.getDate()));
        ps.setString(2, transactionHistory.getType().name());
        ps.setString(3,transactionHistory.getDescription());
        ps.setDouble(4,transactionHistory.getValue());
        ps.setInt(5,transactionHistory.getTransactionId().getId());
        ps.setString(6, transactionHistory.getTransactionType().name());
        ps.setInt(7,transactionHistory.getUserId().getId());
        ps.executeUpdate();
        System.out.println("TransactionHistory created");
    }catch (Exception e){
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<TransactionHistory> showTransactionHistory() {
        String sql = "SELECT * FROM transaction_history";
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {


                LocalDate date = rs.getDate("date").toLocalDate();
                TransactionType transactionType = TransactionType.valueOf(rs.getString("type"));
                HistoryType historyType = HistoryType.valueOf(rs.getString("history_type"));
                User user = new UserDAOImpl().getUser(rs.getInt("user_id"));
                Transaction transaction = new TransactionDAOImpl().getTransaction(rs.getInt("transaction_id"));

                transactionHistoryList.add(new TransactionHistory(
                        rs.getInt("id"),
                        date,
                        historyType,
                        rs.getString("description"),
                        rs.getDouble("value"),
                        transaction,
                        transactionType,
                        user
                ));


            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return transactionHistoryList;

    }


    @Override
    public void updateTransactionHistory(TransactionHistory transactionHistory) {
        String sql = "UPDATE transaction_history SET date = ?,type = ?,description = ?,value = ?,transaction_id= ?,transaction_type = ?,user_id = ? WHERE id = ?";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDate(1, java.sql.Date.valueOf(transactionHistory.getDate()));
            ps.setString(2, transactionHistory.getDescription());
            ps.setDouble(3,transactionHistory.getValue());
            ps.setInt(4,transactionHistory.getTransactionId().getId());
            ps.setString(5, transactionHistory.getTransactionType().name());
            ps.setInt(6,transactionHistory.getUserId().getId());
            ps.setInt(7,transactionHistory.getId());
            ps.executeUpdate();
            System.out.println("TransactionHistory updated");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransactionHistory getTransactionHistory(int id) {
        String sql = "SELECT * FROM transaction_history WHERE id = ?";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                TransactionType transactionType = TransactionType.valueOf(rs.getString("type"));
                HistoryType historyType = HistoryType.valueOf(rs.getString("history_type"));
                User user = new UserDAOImpl().getUser(rs.getInt("user_id"));
                Transaction transaction = new TransactionDAOImpl().getTransaction(rs.getInt("transaction_id"));
                return new TransactionHistory(rs.getInt("id"),date, historyType, rs.getString("description"), rs.getDouble("value"), transaction, transactionType, user);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteTransactionHistory(int id) {
        String sql = "DELETE FROM transaction_history WHERE id = ?";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("TransactionHistory deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

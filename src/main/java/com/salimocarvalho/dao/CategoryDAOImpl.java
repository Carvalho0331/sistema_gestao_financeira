package com.salimocarvalho.dao;

import com.salimocarvalho.db.ConnectionDB;
import com.salimocarvalho.enums.TransactionType;
import com.salimocarvalho.model.Category;

import com.salimocarvalho.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void createCategory(Category category) {
        String sql = "INSERT INTO category (name, type, user_id) VALUES (?,?,?) RETURNING id";
        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,  category.getName());
            ps.setString(2, category.getType().name());
            ps.setInt(3, category.getUser().getId());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                category.setId(rs.getInt("id"));
            }
            System.out.println("Categoria criado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getCategory(int id) {
        String sql = "select * from Category where id = ?";
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeQuery();

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Category> showAllCategories() {


        String sql = "select * from Category";
        List<Category> categories = new ArrayList<>();
        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                int userId = rs.getInt("user_id");

                User user = new UserDAOImpl().getUser(userId);

                TransactionType transactionType = TransactionType.valueOf(rs.getString("type"));

                categories.add(new Category(rs.getInt("id"),rs.getString("name"),transactionType, user));

            };


        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        String sql = "UPDATE category SET name = ?, type = ?, user_id = ? WHERE id = ?";

        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,  category.getName());
            ps.setString(2,category.getType().name());
            ps.setInt(3, category.getUser().getId());
            ps.setInt(4, category.getId());

           ps.executeUpdate();
           System.out.println("Categoria actualizado com sucesso!");

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE from Category where id = ?";

        try(Connection con = ConnectionDB.getConnection();
        PreparedStatement ps =con.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Categoria apagado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.salimocarvalho.dao;

import com.salimocarvalho.db.ConnectionDB;
import com.salimocarvalho.enums.Profile;
import com.salimocarvalho.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, email, password,profile) VALUES (?, ?, ?,?) RETURNING id";

        try(
                Connection con = ConnectionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getProfile().name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
            }
            System.out.println("Utilizador criado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
    }

    @Override
    public User getUser(int id) {


        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Profile.valueOf(rs.getString("profile"))
                );

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public List<User> showAllUsers() {
        List <User> users = new java.util.ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
          ResultSet rs = ps.executeQuery();

          while(rs.next()) {
              users.add(new User(
                      rs.getInt("id"),
                      rs.getString("name"),
                      rs.getString("email"),
                      rs.getString("password"),
                      Profile.valueOf(rs.getString("profile"))
              ));
          }


        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, profile = ? WHERE id = ?";

        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement ps =  con.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getProfile() != null ? user.getProfile().name() : null);
            ps.setInt(5, user.getId());
            int rowAffected = ps.executeUpdate();

            if(rowAffected > 0) {
                System.out.println("Utilizador atualizado com sucesso!");
            }else {
                System.out.println("Nenhum usuário encontrado com esse ID.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();

            if(rowAffected > 0) {
                System.out.println("Utilizador deletado com sucesso!");
            }else {
                System.out.println("Nenhum utilizador encontrado com esse ID.");
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}


package com.salimocarvalho;

import com.salimocarvalho.db.ConnectionDB;
import com.salimocarvalho.enums.Profile;
import com.salimocarvalho.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();


        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Preenche todos os campos");
            return;
        }

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){


            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();


            if(rs.next()){

                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Profile.valueOf(rs.getString("profile")));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();

                mainController controller = loader.getController();
                controller.setUser(user);

                Stage stage = new Stage();
                stage.setTitle("Sistema de gestao Financeira");
                stage.setScene(new Scene(root));
                stage.show();

                Stage loginStage = (Stage) emailField.getScene().getWindow();
                loginStage.close();
            }else {
                errorLabel.setText("Preenche todos os campos");
            }



        }catch (Exception e){
            throw  new RuntimeException(e);
        }

        // por agora só imprime
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
}
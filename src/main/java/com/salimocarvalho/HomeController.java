package com.salimocarvalho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML private Button loginButton;
    @FXML private Button registerButton;


    @FXML
    private void handleRegister()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign_in.fxml"));
        Parent root =  loader.load();


        Stage stage = new Stage();
        stage.setTitle("Register");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) registerButton.getScene().getWindow();
        stage1.close();


    }
    @FXML
    private void handleLogin() throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) loginButton.getScene().getWindow();
        stage2.close();

    }

}

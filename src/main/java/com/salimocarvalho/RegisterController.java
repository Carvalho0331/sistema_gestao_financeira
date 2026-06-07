package com.salimocarvalho;

import com.salimocarvalho.dao.UserDAO;
import com.salimocarvalho.dao.UserDAOImpl;
import com.salimocarvalho.enums.Profile;
import com.salimocarvalho.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordConfirmField;
    @FXML private ChoiceBox<Profile> profileChoice;
    @FXML private Label errorLabel;


    @FXML
    public void initialize(){
        profileChoice.getItems().addAll(Profile.values());
        profileChoice.getSelectionModel().select(0);
    }


    @FXML
    private void handleRegister(){


     String name = nameField.getText();
     String email = emailField.getText();
     String password = passwordField.getText();
     String passwordConfirm = passwordConfirmField.getText();
     Profile profile = profileChoice.getValue();


     if(!password.equals(passwordConfirm)){
         errorLabel.setText("Passwords don't match!");
         return;
     }

     if(name.isEmpty() && email.isEmpty() && password.isEmpty() && passwordConfirm.isEmpty()){
         errorLabel.setText("Please fill all the fields");
         return;
     }

    UserDAO userDAO = new UserDAOImpl();

    User user = new User(0,name,email,password,profile);
    userDAO.createUser(user);
    System.out.println("User created");


    try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage2 = (Stage) nameField.getScene().getWindow();
        stage2.close();

    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    }
}

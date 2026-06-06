package com.salimocarvalho;

import com.salimocarvalho.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class mainController {
@FXML private Label welcomeLabel;
@FXML private AnchorPane contentArea;
@FXML private Button dashboardButton;
@FXML private Button receitasButton;
@FXML private Button despesasdButton;
@FXML private Button relatoriosdButton;


private User currentUser;

public void setUser(User user) {
    this.currentUser = user;
    welcomeLabel.setText("Seja bem vindo, " + user.getName() + " ao Dashboard");

}


@FXML private void handleDashboard(){
    System.out.println("Dashboard");
}
@FXML private void handleReceitas(){
    System.out.println("Receitas");
}
@FXML private void handleDespesas(){
    System.out.println("Despesas");
}
@FXML private void handleRelatorios(){
    System.out.println("Relatorios");
}

}

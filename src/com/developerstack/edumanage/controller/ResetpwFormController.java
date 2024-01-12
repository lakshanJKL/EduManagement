package com.developerstack.edumanage.controller;

import com.developerstack.edumanage.DB.Databse;
import com.developerstack.edumanage.model.UserModel;
import com.developerstack.edumanage.util.security.PasswordManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class ResetpwFormController {
    public TextField txtxchngepw;
    public AnchorPane contextresetpw;
    String selectedemail = "";

    public void setuserdata(String email){
        selectedemail = email;

    }
    public void changepwOnAction(ActionEvent actionEvent) throws IOException {


        if(Databse.usertable.get(2).getEmail().equals(selectedemail)){
            Databse.usertable.get(2).setEmail(new PasswordManger().encrypt(txtxchngepw.getText()));
            Setui("LoginForm");
        }else {
            new Alert(Alert.AlertType.WARNING,"not found").show();
        }
    }
    private void Setui(String location) throws IOException {
        contextresetpw.getChildren().clear();
        Stage stage =(Stage) contextresetpw.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}

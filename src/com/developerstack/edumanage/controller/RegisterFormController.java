package com.developerstack.edumanage.controller;

import com.developerstack.edumanage.DB.Databse;
import com.developerstack.edumanage.model.UserModel;
import com.developerstack.edumanage.util.security.PasswordManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class RegisterFormController {
    public TextField fristname;
    public TextField email;
   
    public TextField lastname;
    public TextField password;
    public AnchorPane contextReg;


    public void sigupOnAction(ActionEvent actionEvent) throws IOException {
        String fristName = fristname.getText();
        String lastName = lastname.getText();
        String Email = email.getText().toLowerCase();
        String Password = new PasswordManger().encrypt(password.getText().trim());

        UserModel user = new UserModel(fristName,lastName,Email,Password);
        try {
            boolean isSaved = signup(user);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Welcome !").show();
                Setui("LoginForm");
            }else {
                new Alert(Alert.AlertType.WARNING,"not found").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }


    }

    public void alreadyaccOnAction(ActionEvent actionEvent) throws IOException {
        Setui("LoginForm");
    }

    private void Setui(String location) throws IOException {
        contextReg.getChildren().clear();
        Stage stage =(Stage) contextReg.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    //=============sql database=========


    private boolean signup(UserModel user) throws ClassNotFoundException, SQLException {
      // load driver
        Class.forName("com.mysql.cj.jdbc.Driver");

     //create connectiom
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms4","root","JK#laka@JKL123");

    // write SQL
        String SQL = "INSERT INTO usser_table VALUES (?,?,?,?)";

    // create statement
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getFristname());
        statement.setString(3, user.getLastname());
        statement.setString(4, user.getPassword());

    //  set sql into the statement & execute
        return statement.executeUpdate() > 0;  // statement.executeUpdate(SQL) is int value  // CAN USE  INSERT, UPDATE, DELETE

    }

}

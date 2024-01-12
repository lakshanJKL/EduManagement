package com.developerstack.edumanage.controller;

import com.developerstack.edumanage.DB.Databse;
import com.developerstack.edumanage.model.UserModel;
import com.developerstack.edumanage.util.security.PasswordManger;
import com.mysql.cj.protocol.Resultset;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class LoginFormcController {
    public TextField txtemail;
    public TextField txtpassword;
    public AnchorPane contextlogin;

    public void frogotpwdOnAction(ActionEvent actionEvent) throws IOException {
        Setui("ForgotpasswordFrom");
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtemail.getText().toLowerCase();
        String password = txtpassword.getText().trim();

//        for(UserModel user:Databse.usertable){
//            if(user.getEmail().equals(email)){
//                if(user.getPassword().equals(password)){
//                    new Alert(Alert.AlertType.INFORMATION,"Login is Success").show();
//                    return;
//                }else{
//                    new Alert(Alert.AlertType.INFORMATION,"Password is Incorrect").show();
//                    return;
//                }
//            }
//        }
        try{
            UserModel selecteduser = login(email);
           if(null!=selecteduser){
               if(new PasswordManger().checkPassowrd(password,selecteduser.getPassword())){
                   System.out.println(selecteduser.getEmail());
                   Setui("Dshaboard");
               }else{
                   new Alert(Alert.AlertType.INFORMATION,"Password is Incorrect").show();
               }
           }

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }

    }

    public void createaccOnAction(ActionEvent actionEvent) throws IOException {
        Setui("RegisterForm");
    }

    private void Setui(String location) throws IOException {
        contextlogin.getChildren().clear();
        Stage stage =(Stage) contextlogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    private UserModel login(String email) throws ClassNotFoundException, SQLException {
        // load driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connnection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms4", "root", "JK#laka@JKL123");
        //sql
        String SQL = "SELECT * FROM usser_table WHERE email=?";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1,email);
        ResultSet resultSet = statement.executeQuery(); // select
        if (resultSet.next()) {
            UserModel user = new UserModel(
                    resultSet.getString("frist_name"),
                    resultSet.getString(2),
                    resultSet.getString("email"),
                    resultSet.getString(4)
            );
            System.out.println(user);
            return user;
        }
        return null;
   }
}

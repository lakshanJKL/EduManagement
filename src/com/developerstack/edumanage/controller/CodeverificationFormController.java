package com.developerstack.edumanage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CodeverificationFormController {
    public AnchorPane contextverify;
    public TextField txtcode;
    public Label selectedemail;
    int code = 0;
    String Selectedemail = "";

    public void setuserdata(int verificationcode,String email){
        System.out.println(verificationcode);
        code = verificationcode;
        Selectedemail=email;
        selectedemail.setText(email);
    }

    public void verifycodeOnAction(ActionEvent actionEvent) throws IOException {
        if(String.valueOf(code).equals(txtcode.getText())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ResetpwForm.fxml"));
            Parent parent = fxmlLoader.load();
            ResetpwFormController comtroller = fxmlLoader.getController();
            comtroller.setuserdata(String.valueOf(selectedemail));
            Stage stage = (Stage) contextverify.getScene().getWindow();
            stage.setScene(new Scene(parent));


        }else{
            new Alert(Alert.AlertType.ERROR,"verfy code is Incorrected").show();
        }
    }

    public void changemailOnAction(ActionEvent actionEvent) throws IOException {
        Setui("ForgotpasswordFrom");
    }

    private void Setui(String location) throws IOException {
        contextverify.getChildren().clear();
        Stage stage =(Stage) contextverify.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}

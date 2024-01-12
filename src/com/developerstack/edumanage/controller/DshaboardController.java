package com.developerstack.edumanage.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DshaboardController {
    public AnchorPane contextdash;
    public Label lbldata;
    public Label lbltime;

    public void initialize(){
        setdateAndtime();
    }

    private void setdateAndtime() {
        lbldata.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),e->{
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                    lbltime.setText(LocalTime.now().format(dateFormatter));
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        Setui("LoginForm");
    }

    private void Setui(String location) throws IOException {
        contextdash.getChildren().clear();
        Stage stage =(Stage) contextdash.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        Setui("StudentForm");
    }
}

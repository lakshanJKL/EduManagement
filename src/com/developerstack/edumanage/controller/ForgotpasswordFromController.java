package com.developerstack.edumanage.controller;

import com.developerstack.edumanage.util.tools.Verificationcodegenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ForgotpasswordFromController {
    public AnchorPane contextforgotpw;
    public TextField verificemail;

    public void sendcodeOnAction(ActionEvent actionEvent) {
        int verificationcode = new Verificationcodegenerator().getcode(5);
        try {
            String fromemail = "lakshanjkl49@gmail.com";
            String toemail = verificemail.getText();
            String host = "localhost";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);// nodemailer  (EE - (sendgrid) mobile-(twilio))

            Session session = Session.getDefaultInstance(properties);

            // create email
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress());
            mimeMessage.setSubject("Verification code");
            mimeMessage.setText("verification code "+" "+verificationcode);
           // mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress(toemail));

            //Transport.send(mimeMessage);
            System.out.println("completed");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/codeverificationForm.fxml"));
            Parent parent = fxmlLoader.load();
            CodeverificationFormController comtroller = fxmlLoader.getController();
            comtroller.setuserdata(verificationcode,verificemail.getText());
            Stage stage = (Stage) contextforgotpw.getScene().getWindow();
            stage.setScene(new Scene(parent));

        }catch (MessagingException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Setui("LoginForm");
    }

    private void Setui(String location) throws IOException {
        contextforgotpw.getChildren().clear();
        Stage stage =(Stage) contextforgotpw.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

}

package com.developerstack.edumanage.controller;

import com.developerstack.edumanage.DB.Databse;
import com.developerstack.edumanage.model.Student;
import com.developerstack.edumanage.view.TM.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.logging.SimpleFormatter;

public class StudentFormController {
    public AnchorPane contextSt;
    public TextField txtid;
    public TextField txtname;
    public TextField txtaddress;
    public DatePicker txtbod;
    public TableView <StudentTM>contextTbl;
    public TableColumn colId;
    public TableColumn colFullName;
    public TableColumn colDob;
    public TableColumn coladdress;
    public TableColumn colOptional;
    public Button btn;
    public TextField searchbx;

    String seachtext = "";

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOptional.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchbx.textProperty().addListener((observable, oldValue, newValue) -> {
            seachtext = newValue;
            setTableData(seachtext);
        });

        setstudentid();
        setTableData(seachtext);

        contextTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setData(newValue);
            }

        });
    }

    private void setData(StudentTM tm) {
        txtid.setText(tm.getId());
        txtname.setText(tm.getFullname());
        txtbod.setValue(LocalDate.parse(tm.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        txtaddress.setText(tm.getAddress());
        btn.setText("Update");
    }

    ObservableList<StudentTM> obserlist= FXCollections.observableArrayList();
    private void setTableData(String seachtext) {
        for (Student st: Databse.studenttable
             ) {
            if(st.getFullname().contains(seachtext)){
                Button btn = new Button("Delete");
                StudentTM studentTM = new StudentTM(
                        st.getStudentid(),
                        st.getFullname(),
                        new SimpleDateFormat("yyyy-MM-dd").format(st.getDob()),
                        st.getAddress(),
                        btn
                );
                btn.setOnAction(e->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are Your Sure ?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType>buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Databse.studenttable.remove(st);
                        new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted").show();
                        setTableData(seachtext);
                    }
                });

                obserlist.add(studentTM);


            }

        }

        contextTbl.setItems(obserlist);
    }

    private void setstudentid() {

        if(!Databse.studenttable.isEmpty()){
            Student laststudent = Databse.studenttable.get(
                    Databse.studenttable.size()-1
            );
            String laststId=laststudent.getStudentid();
            String [] stridno = laststId.split("-");
            String strintno = stridno[1];
            int idnumber = Integer.parseInt(strintno);
//            int i = ++idnumber;
            String generatestr = "S-"+(++idnumber) ;
            txtid.setText(generatestr);

        }else{
            txtid.setText("S-1");
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {

        if(btn.getText().equalsIgnoreCase("Save")){

            Student student = new Student(
                    txtid.getText(),
                    txtname.getText(),
                    Date.from(txtbod.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    txtaddress.getText()
            );

            Databse.studenttable.add(student);
            new Alert(Alert.AlertType.INFORMATION,"Student Saved").show();
            Clear();
            setTableData(seachtext);

        }else{
           Optional<Student> SelectedStudent= Databse.studenttable.stream().filter(e->e.getStudentid().equals(txtid.getText())).findFirst();
           if(!SelectedStudent.isPresent()){
               new Alert(Alert.AlertType.WARNING,"Student Not Found").show();
               return;
           }
            SelectedStudent.get().setAddress(txtaddress.getText());
            SelectedStudent.get().setDob(Date.from(txtbod.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            SelectedStudent.get().setFullname(txtname.getText());
            setTableData(seachtext);
            Clear();
            setstudentid();

        }

    }

    private void Clear(){
        txtid.clear();
        txtname.clear();
        txtaddress.clear();
        txtbod.setValue(null);
    }

    public void newstOnAction(ActionEvent actionEvent) {
        Clear();
        setstudentid();
        btn.setText("Save");
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Setui("Dshaboard");
    }
    private void Setui(String location) throws IOException {
        contextSt.getChildren().clear();
        Stage stage =(Stage) contextSt.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}

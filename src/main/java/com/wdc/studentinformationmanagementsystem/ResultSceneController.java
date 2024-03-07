package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class ResultSceneController implements Initializable {
    @FXML
    private Label numOfInfo;
    @FXML
    private TableColumn<StudentWithNumber, String> classCol;
    @FXML
    private TableView<StudentWithNumber> form;
    @FXML
    private TableColumn<StudentWithNumber, String> gender;
    @FXML
    private TableColumn<StudentWithNumber, String> name;
    @FXML
    private TableColumn<StudentWithNumber, String> number;
    @FXML
    private TableColumn<StudentWithNumber, String> studentNumber;
    @FXML
    private Label title;
    
    private Vector<Integer> indexesOfStudent = new Vector<>();

    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        form.setEditable(true);
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Label label = new Label("无搜索结果");
        label.setFont(new Font(15));
        form.setPlaceholder(label);
    }
    public void search(String key, String value){
        switch (key){
            case "序号" -> {
                int index;
                try {
                    index = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-错误",
                            "输入的序号不是数字");
                    alert.showAndWait();
                    form.getScene().getWindow().hide();
                    return;
                }
                form.getItems().add(new StudentWithNumber(index-1, Value.students.elementAt(index-1)));
                indexesOfStudent.add(index-1);
            }case "姓名" -> {
                for (int i = 0;i < Value.students.capacity();i++){
                    Student s = Value.students.elementAt(i);
                    if (s.name.contains(value)){
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "性别" -> {
                for (int i = 0;i < Value.students.capacity();i++){
                    Student s = Value.students.elementAt(i);
                    if (s.gender.contains(value)){
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "班级" -> {
                for (int i = 0;i < Value.students.capacity();i++){
                    Student s = Value.students.elementAt(i);
                    if (s.studentClass.contains(value)) {
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "学号" -> {
                for (int i = 0;i < Value.students.capacity();i++){
                    Student s = Value.students.elementAt(i);
                    if (s.studentNumber.contains(value)) {
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }
        }
    }
    @FXML
    public void delSelected(ActionEvent actionEvent){
        
    }
    @FXML
    public void delAll(ActionEvent actionEvent){
        
    }
}

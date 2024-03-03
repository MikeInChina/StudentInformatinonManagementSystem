package com.wdc.studentinformationmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultSceneController implements Initializable {
    @FXML
    private TableColumn<Student, String> classCol;
    @FXML
    private TableView<Student> form;
    @FXML
    private TableColumn<Student, String> gender;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> number;
    @FXML
    private TableColumn<Student, String> studentNumber;
    @FXML
    private Label title;

    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Value.initForm(form, number, name, gender, classCol, studentNumber);
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
                form.getItems().add(Value.students.elementAt(index-1));
            }case "姓名" -> {
                for (Student s : Value.students){
                    if (s.name.contains(value)) form.getItems().add(s);
                }
            }case "性别" -> {
                for (Student s : Value.students){
                    if (s.gender.contains(value)) form.getItems().add(s);
                }
            }case "班级" -> {
                for (Student s : Value.students){
                    if (s.shift.contains(value)) form.getItems().add(s);
                }
            }case "学号" -> {
                for (Student s : Value.students){
                    if (s.studentNumber.contains(value)) form.getItems().add(s);
                }
            }
        }
    }
}

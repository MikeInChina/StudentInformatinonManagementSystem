package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Optional;
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
    private String key, value;
    
    private final Vector<Integer> indexesOfStudent = new Vector<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        form.setEditable(true);
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setSortable(false);
        studentNumber.setSortable(false);
        classCol.setSortable(false);
        gender.setSortable(false);
        name.setSortable(false);
        Label label = new Label("无搜索结果");
        label.setFont(new Font(15));
        form.setPlaceholder(label);
    }
    public void search(String key, String value){
        this.key = key;
        this.value = value;
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
                form.getItems().add(new StudentWithNumber(index, Value.students.elementAt(index-1)));
                indexesOfStudent.add(index-1);
            }case "姓名" -> {
                for (int i = 0;i < Value.students.size();i++){
                    Student s;
	                s = Value.students.elementAt(i);
                    if (s.name.contains(value)){
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "性别" -> {
                for (int i = 0;i < Value.students.size();i++){
                    Student s;
	                s = Value.students.elementAt(i);
                    if (s.gender.contains(value)){
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "班级" -> {
                for (int i = 0;i < Value.students.size();i++){
                    Student s;
	                s = Value.students.elementAt(i);
                    if (s.studentClass.contains(value)) {
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }case "学号" -> {
                for (int i = 0;i < Value.students.size();i++){
                    Student s;
	                s = Value.students.elementAt(i);
                    if (s.studentNumber.contains(value)) {
                        form.getItems().add(new StudentWithNumber(i + 1, s));
                        indexesOfStudent.add(i);
                    }
                }
            }
        }
        numOfInfo.setText("搜索到%d条信息".formatted(indexesOfStudent.size()));
    }
    @FXML
    public void delSelected(ActionEvent actionEvent){
        // index是在当前表格里要删除的行的位置
        int index = form.getSelectionModel().getSelectedIndex();
        if (index == -1) return;
        Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION, "学生信息管理系统-询问",
                "是否要删除第 " + (indexesOfStudent.elementAt(index) + 1) + " 行?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            Value.students.remove(indexesOfStudent.elementAt(index).intValue());
            StudentInformationManagementSystem.mainController.getForm().getItems()
                    .remove(indexesOfStudent.elementAt(index).intValue());
            for (int i = index; i < indexesOfStudent.size(); i++) {
                indexesOfStudent.set(i, indexesOfStudent.elementAt(i) - 1);
                form.getItems().get(i).setNumber(form.getItems().get(i).getNumber() - 1);
            }
            form.getItems().remove(index);
            indexesOfStudent.remove(index);
            Alert info = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
                    "删除成功！");
            info.showAndWait();
        }
    }
    @FXML
    public void delAll(ActionEvent actionEvent){
        Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION, "学生信息管理系统-询问",
                "是否要删除 \"%s\" 中包含 \"%s\" 的所有行？".formatted(key, value));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            for (int i = indexesOfStudent.size()-1; i >= 0; i--) {
                form.getItems().remove(i);
                Value.students.remove(indexesOfStudent.elementAt(i).intValue());
                StudentInformationManagementSystem.mainController.getForm().getItems()
                        .remove(indexesOfStudent.elementAt(i).intValue());
                indexesOfStudent.remove(i);
            }
            Alert info = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
                    "删除成功！");
            info.showAndWait();
        }
    }
}

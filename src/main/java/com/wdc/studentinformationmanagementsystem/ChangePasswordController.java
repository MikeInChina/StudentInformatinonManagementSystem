package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {
    @FXML
    private PasswordField field1;
    @FXML
    private PasswordField field2;
    @FXML
    private PasswordField field3;
    @FXML
    private Label lastLabel;
    @FXML
    private Label newLabel;
    @FXML
    private Label sureLabel;
    @FXML
    void cancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    void ok(ActionEvent event) {
        if (Value.isAdmin && Value.hash(field1.getText()) == Value.adminPassword &&
                field2.getText().equals(field3.getText())){
            Value.adminPassword = Value.hash(field2.getText());
            Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示", "修改成功!");
            alert.showAndWait();
            ((Node)event.getSource()).getScene().getWindow().hide();
        } else if (!Value.isAdmin && Value.hash(field1.getText()) == Value.studentPassword &&
                field2.getText().equals(field3.getText())) {
            Value.studentPassword = Value.hash(field2.getText());
            Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示", "修改成功!");
            alert.showAndWait();
            ((Node)event.getSource()).getScene().getWindow().hide();
        } else {
            Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示",
                    "输入的密码与原密码不一致或两次输入的新密码不一致！");
            alert.showAndWait();
        }
    }

}

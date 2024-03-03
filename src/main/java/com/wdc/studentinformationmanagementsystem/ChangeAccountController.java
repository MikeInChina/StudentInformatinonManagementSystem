package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeAccountController {
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
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
        if (field1.getText().equals(Value.adminAccount) && field2.getText().equals(field3.getText())){
            Value.adminAccount = field2.getText();
            Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示", "修改成功!");
            alert.showAndWait();
            ((Node)event.getSource()).getScene().getWindow().hide();
        }else {
            Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示",
                    "输入的账号与原账号不一致或两次输入的新账号不一致！");
            alert.showAndWait();
        }
    }
}

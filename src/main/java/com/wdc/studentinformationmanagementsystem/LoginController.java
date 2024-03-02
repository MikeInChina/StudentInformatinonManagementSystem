package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 登录界面的控制器类，控制登录界面的事件处理
 */
public class LoginController {

	@FXML
	private TextField accountBox;

	@FXML
	private Button adminLoginBtn;

	@FXML
	private Button exit;

	@FXML
	private PasswordField passwordBox;

	@FXML
	private Button studentLoginBtn;

	@FXML
	void adminLogin(ActionEvent event) {
		if (accountBox.getText().equals(Value.adminAccount) &&
				passwordBox.getText().equals(Value.adminPassword)){
			try {
				StudentInformationManagementSystem.setMainScene(true);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("学生信息管理系统-提示");
			alert.setContentText("用户名或密码不正确！");
			alert.setHeaderText(null);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().setAll(Value.icon);
			alert.showAndWait();
		}
	}

	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void focusToPasswordBox(ActionEvent event) {
		passwordBox.requestFocus();
	}

	@FXML
	void studentLogin(ActionEvent event) {
		try {
			StudentInformationManagementSystem.setMainScene(false);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

	}

}

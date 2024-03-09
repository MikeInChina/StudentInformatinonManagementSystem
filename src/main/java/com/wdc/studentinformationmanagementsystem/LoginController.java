package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
		if (Value.hash(accountBox.getText()) == Value.adminAccount &&
				Value.hash(passwordBox.getText()) == Value.adminPassword){
			try {
				StudentInformationManagementSystem.setMainScene(true);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else {
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示",
					"用户名或密码不正确！");
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
		System.out.println(passwordBox.getText());
		for (Student s : Value.students){
			if (s.studentNumber.equals(accountBox.getText())){
				int passwordHash = getPasswordHash(s);
				if (Value.hash(passwordBox.getText()) == passwordHash) {
					try {
						Value.studentAccount = accountBox.getText();
						StudentInformationManagementSystem.setMainScene(false);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}else {
					Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示",
							"用户名或密码不正确！");
					alert.showAndWait();
				}

				return;
			}
		}
		Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "无此用户!");
		alert.showAndWait();
	}

	private static int getPasswordHash(Student s) {
		int passwordHash;
		File file = new File("data/" + s.studentNumber + ".txt");
		if (!file.exists()){
			try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
					StandardCharsets.UTF_8))){
				file.createNewFile();
				fw.write(Value.studentPassword);
				passwordHash = Value.studentPassword;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else{
			try (BufferedReader br = new BufferedReader(new FileReader(file))){
				String line = br.readLine();
				passwordHash = Integer.parseInt(line);
			}catch (IOException e){
				throw new RuntimeException(e);
			}
		}
		return passwordHash;
	}

}

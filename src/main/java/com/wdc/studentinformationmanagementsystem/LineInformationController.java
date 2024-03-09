package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LineInformationController {

	@FXML
	private Button cancelBtn;

	@FXML
	private TextField classField;

	@FXML
	private Button continueBtn;

	@FXML
	private TextField genderField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField studentNumberField;

	StudentDataCallBack callBack;

	public void setCallBack(StudentDataCallBack callBack) {
		this.callBack = callBack;
	}

	@FXML
	void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void create(ActionEvent event) {
		Student student = new Student(studentNumberField.getText(), nameField.getText(),
				genderField.getText(), classField.getText());
		if (student.getName().isEmpty() || student.getStudentNumber().isEmpty() || student.getGender().isEmpty() ||
				student.getStudentClass().isEmpty()){
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "学生信息不能为空！");
			alert.showAndWait();
			return;
		}
		callBack.onStudentDataReceived(student);
	}

}

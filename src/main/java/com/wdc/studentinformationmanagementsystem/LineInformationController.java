package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
		if (student.getStudentNumber().isEmpty()) student.setStudentNumber("-");
		if (student.getGender().isEmpty()) student.setGender("-");
		if (student.getName().isEmpty()) student.setName("-");
		if (student.getShift().isEmpty()) student.setShift("-");
		callBack.onStudentDataReceived(student);
	}

}

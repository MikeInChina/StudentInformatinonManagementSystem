package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LineMessageController {

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

	DataCallBack callBack;

	public void setCallBack(DataCallBack callBack) {
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
		callBack.onDataReceived(student);
	}

}

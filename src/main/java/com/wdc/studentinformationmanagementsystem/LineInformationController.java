package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern patternForGender = Pattern.compile("^([男女])$");
		Matcher matcherForGender = patternForGender.matcher(genderField.getText());
		if (!matcherForGender.find()){
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "性别只能为男或女！");
			alert.showAndWait();
			return;
		}
		Pattern patternForNumber = Pattern.compile("^(\\d+)$");
		Matcher matcherForNumber = patternForNumber.matcher(studentNumberField.getText());
		if (!matcherForNumber.find()){
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "学号必须为数字！");
			alert.showAndWait();
			return;
		}
		Pattern patternForClass = Pattern.compile("^(\\d+年\\d+班)$");
		Matcher matcherForClass = patternForClass.matcher(classField.getText());
		if (!matcherForClass.find()){
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "班级信息不符合格式！" +
					"\n格式：x年x班（其中x为数字）");
			alert.showAndWait();
			return;
		}
		callBack.onStudentDataReceived(student);
	}

}

package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
	@FXML
	private TextField box;
	@FXML
	private ComboBox<String> option;
	@FXML
	void cancel(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void ok(ActionEvent event) {
		String chosen = option.getValue();
		if (chosen == null){
			Alert alert = Value.createAlert(Alert.AlertType.ERROR, "学生信息管理系统-提示", "请选择查找的类别！");
			alert.showAndWait();
			return;
		}
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("result-view.fxml"));
		Stage stage = MainController.getStageFromLoader(loader, 700, 500);
		stage.setTitle("学生信息管理系统-搜索结果");
		stage.show();
		ResultSceneController resultSceneController = loader.getController();
		resultSceneController.search(chosen, box.getText());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		option.getItems().setAll("序号", "学号", "姓名", "性别", "班级");
	}
}

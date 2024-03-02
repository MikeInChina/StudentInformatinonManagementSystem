package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 主界面的控制器类，控制主界面的事件处理
 */
public class MainController implements Initializable {

	@FXML
	private Button delRow;
	@FXML
	private Button filterBtn;
	@FXML
	private Button newRowBtn;
	@FXML
	private Button searchBtn;
	@FXML
	public TableView<Student> form;
	@FXML
	public TableColumn<Student, String> number;
	@FXML
	public TableColumn<Student, String> name;
	@FXML
	public TableColumn<Student, String> studentNumber;
	@FXML
	public TableColumn<Student, String> classCol;
	@FXML
	public TableColumn<Student, String> gender;
	@FXML
	void createRow(ActionEvent event) {
		Stage popUpStage = new Stage();
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("line_msg-view.fxml"));
		Scene scene;
		try {
			scene = new Scene(loader.load(), 400, 300);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		LineMessageController lineMessageController = loader.getController();
		lineMessageController.setCallBack((student) -> {
			form.getItems().add(student);
			popUpStage.close();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("学生信息管理系统-提示");
			alert.setContentText("创建成功！");
			alert.setHeaderText(null);
			alert.showAndWait();
		});
		popUpStage.setScene(scene);
		popUpStage.initModality(Modality.APPLICATION_MODAL);
		popUpStage.setTitle("学生信息管理系统-请输入");
		popUpStage.getIcons().setAll(Value.icon);
		popUpStage.show();
	}


	@FXML
	void delRow(ActionEvent event) {
		int index = form.getSelectionModel().getSelectedIndex();
		if (index == -1) return;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("学生信息管理系统-询问");
		alert.setHeaderText(null);
		alert.setContentText("是否要删除行" + (index + 1) + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get().equals(ButtonType.OK)){
			form.getItems().remove(index);
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("学生信息管理系统-询问");
			info.setHeaderText(null);
			info.setContentText("删除成功！");
			info.showAndWait();
		}
	}

	@FXML
	void filter(ActionEvent event) {

	}

	@FXML
	void search(ActionEvent event) {

	}

	public void load(ActionEvent actionEvent) {

	}

	public void openSettings(ActionEvent actionEvent) {

	}

	public void exit(ActionEvent actionEvent) {
		System.exit(0);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		form.setEditable(true);
		number.setCellFactory((tableColumn) -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				this.setText(null);
				this.setGraphic(null);
				if (!empty) {
					this.setText(String.valueOf(this.getIndex() + 1));
				}
			}
		});
		studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
		classCol.setCellValueFactory(new PropertyValueFactory<>("shift"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		studentNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		studentNumber.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
			Student student = t.getTableView().getItems().get(t.getTablePosition().getRow());
			student.setStudentNumber(t.getNewValue());
		});
		classCol.setCellFactory(TextFieldTableCell.forTableColumn());
		classCol.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
			Student student = t.getTableView().getItems().get(t.getTablePosition().getRow());
			student.setShift(t.getNewValue());
		});
		gender.setCellFactory(TextFieldTableCell.forTableColumn());
		gender.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
			Student student = t.getTableView().getItems().get(t.getTablePosition().getRow());
			student.setGender(t.getNewValue());
		});
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
			Student student = t.getTableView().getItems().get(t.getTablePosition().getRow());
			student.setName(t.getNewValue());
		});
	}
}

package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * 主界面的控制器类，控制主界面的事件处理
 */
public class MainController implements Initializable {
	@FXML
	private MenuItem changeAccountMenuItem;
	@FXML
	private MenuItem importMenuItem;
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
			scene = new Scene(loader.load(), 400, 200);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		LineMessageController lineMessageController = loader.getController();
		lineMessageController.setCallBack((student) -> {
			form.getItems().add(student);
			Value.students.add(student);
			popUpStage.close();
			Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示", "创建成功！");
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
		Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION, "学生信息管理系统-询问",
				"是否要删除第 " + (index + 1) + " 行?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get().equals(ButtonType.OK)){
			form.getItems().remove(index);
			Value.students.remove(index);
			Alert info = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示", "删除成功！");
			info.showAndWait();
		}
	}

	@FXML
	void filter(ActionEvent event) {
		// TODO
	}

	@FXML
	void search(ActionEvent event) {
		// TODO
	}

	public void load(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("学生信息管理系统-选择文件");
		FileChooser.ExtensionFilter extensionFilter =
				new FileChooser.ExtensionFilter("文本文件(*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);

		File selectedFile = fileChooser.showOpenDialog(StudentInformationManagementSystem.primaryStage);
		if (selectedFile == null) return;

		Vector<String[]> information = new Vector<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile),
				StandardCharsets.UTF_8))){
			String line;
			while ((line = br.readLine()) != null){
				String[] strings = line.split(" ");
				information.add(strings);
			}
		}catch (IOException e){
			throw new RuntimeException(e);
		}

		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("choose_col-view.fxml"));
		Scene scene;
		try {
			scene = new Scene(loader.load(), 400, 250);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ChooseColController controller = loader.getController();
		controller.setCols(information.get(0));
		controller.setDataCallBack((ints) -> {
			for(String[] strings : information){
				Student student = new Student("", "", "", "");
				if (ints[0] != -1) student.setStudentNumber(strings[ints[0]-1]);
				if (ints[1] != -1) student.setName(strings[ints[1]-1]);
				if (ints[2] != -1) student.setGender(strings[ints[2]-1]);
				if (ints[3] != -1) student.setShift(strings[ints[3]-1]);
				form.getItems().add(student);
				Value.students.add(student);
			}
		});

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("学生信息管理系统-选取文件列");
		stage.getIcons().setAll(Value.icon);
		stage.show();
	}

	@FXML
	void changeAccount(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("change_account-view.fxml"));
		Scene scene;
		try {
			scene = new Scene(loader.load(), 400, 200);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		Stage cAStage = new Stage();
		cAStage.setScene(scene);
		cAStage.setTitle("学生信息管理系统-更改账号");
		cAStage.getIcons().setAll(Value.icon);
		cAStage.show();
	}

	@FXML
	void changePassword(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("change_password-view.fxml"));
		Scene scene;
		try {
			scene = new Scene(loader.load(), 400, 200);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		Stage cPStage = new Stage();
		cPStage.setScene(scene);
		cPStage.setTitle("学生信息管理系统-更改密码");
		cPStage.getIcons().setAll(Value.icon);
		cPStage.show();
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
		for (Student s : Value.students){
			form.getItems().add(s);
		}
	}
	public void notAdmin(){
		delRow.setDisable(true);
		newRowBtn.setDisable(true);
		searchBtn.setDisable(true);
		filterBtn.setDisable(true);
		importMenuItem.setDisable(true);
		changeAccountMenuItem.setDisable(true);
		Value.isAdmin = false;
	}
}

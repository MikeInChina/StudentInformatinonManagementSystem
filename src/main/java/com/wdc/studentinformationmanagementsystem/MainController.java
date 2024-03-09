package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
	private Button importBtn;
	@FXML
	private Button exportBtn;
	@FXML
	private Button modeSwitcher;
	@FXML
	private MenuItem changeAccountMenuItem;
	@FXML
	private Button delRow;
	@FXML
	private Button newRowBtn;
	@FXML
	private Button searchBtn;
	@FXML
	private TableView<Student> form;
	@FXML
	private TableColumn<Student, String> number;
	@FXML
	private TableColumn<Student, String> name;
	@FXML
	private TableColumn<Student, String> studentNumber;
	@FXML
	private TableColumn<Student, String> classCol;
	@FXML
	private TableColumn<Student, String> gender;

	Boolean isSingleSelectionMode = true;

	public TableView<Student> getForm() {
		return form;
	}

	public static Stage getStageFromLoader(FXMLLoader loader, double width, double height) {
		Scene scene;
		try {
			scene = new Scene(loader.load(), width, height);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().setAll(Value.icon);
		return stage;
	}

	@FXML
	void createRow(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("line_msg-view.fxml"));
		Stage stage = getStageFromLoader(loader, 400, 200);
		LineInformationController lineInformationController = loader.getController();
		lineInformationController.setCallBack((student) -> {
			boolean result = Value.addStudent(student, form);
			Alert alert;
			if (result) alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
						"添加学生信息成功！");
			else alert = Value.createAlert(Alert.AlertType.WARNING, "学生信息管理系统-提示",
						"添加失败，学号须唯一！");
			alert.showAndWait();
			stage.close();
		});
		stage.setTitle("学生信息管理系统-请输入");
		stage.show();
	}


	@FXML
	void delRow(ActionEvent event) {
		if (isSingleSelectionMode){
			int index = form.getSelectionModel().getSelectedIndex();
			if (index == -1) return;
			Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION, "学生信息管理系统-询问",
					"是否要删除第 " + (index + 1) + " 行?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get().equals(ButtonType.OK)){
				form.getItems().remove(index);
				Value.students.remove(index);
				Alert info = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
						"删除成功！");
				info.showAndWait();
			}
		}else {
			Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION, "学生信息管理系统-询问",
					"是否要删除选中的所有行？");
			Optional<ButtonType> result = alert.showAndWait();
			Vector<Integer> indexes = new Vector<>(form.getSelectionModel().getSelectedIndices().stream().toList());
			if (indexes.isEmpty()) return;
			if (result.isPresent() && result.get().equals(ButtonType.OK)){
				for (int i = indexes.size() - 1; i >= 0; i--) {
					int index = indexes.get(i);
					form.getItems().remove(index);
					Value.students.remove(index);
				}
				Alert info = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
						"删除成功！");
				info.showAndWait();
			}
		}
	}

	@FXML
	void search(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("search-view.fxml"));
		Stage stage = getStageFromLoader(fxmlLoader, 350, 100);
		stage.setTitle("学生信息管理系统-搜索");
		stage.show();
	}
	@FXML
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
		final int[] failCnt = {0};
		final int[] succeedCnt = {0};
		ChooseColController controller = loader.getController();
		controller.setCols(information.get(0));
		controller.setDataCallBack((ints) -> {
			for(String[] strings : information){
				Student student = new Student("-", "-", "-", "-");
				if (ints[0] != -1) student.setStudentNumber(strings[ints[0]-1]);
				if (ints[1] != -1) student.setName(strings[ints[1]-1]);
				if (ints[2] != -1) student.setGender(strings[ints[2]-1]);
				if (ints[3] != -1) student.setStudentClass(strings[ints[3]-1]);
				boolean result = Value.addStudent(student, form);
				if (!result) failCnt[0]++;
				else succeedCnt[0]++;
			}
			Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
					"已成功添加%d条学生信息，因学号重复或格式不符合要求而未成功添加%d条。".formatted(succeedCnt[0], failCnt[0]));
			alert.showAndWait();
		});

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("学生信息管理系统-选取文件列");
		stage.getIcons().setAll(Value.icon);
		stage.show();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@FXML
	public void save(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("学生信息管理系统-选择文件");
		FileChooser.ExtensionFilter extensionFilter =
				new FileChooser.ExtensionFilter("文本文件(*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);
		File selectedFile = fileChooser.showSaveDialog(StudentInformationManagementSystem.primaryStage);
		if (selectedFile != null){
			int index = 0;
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile),
					StandardCharsets.UTF_8))){
				selectedFile.createNewFile();
				for (Student s : Value.students){
					// 学号 姓名 性别 班级
					bw.write("%s %s %s %s".formatted(s.studentNumber, s.name, s.gender, s.studentClass));
					bw.newLine();
					index++;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			Alert alert = Value.createAlert(Alert.AlertType.INFORMATION, "学生信息管理系统-提示",
					"成功保存%d条学生数据！".formatted(index));
			alert.showAndWait();
		}

	}

	@FXML
	void changeAccount(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("change_account-view.fxml"));
		Stage stage = getStageFromLoader(loader, 400, 200);
		stage.setTitle("学生信息管理系统-更改账号");
		stage.show();
	}

	@FXML
	void changePassword(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("change_password-view.fxml"));
		Stage stage = getStageFromLoader(loader, 400, 200);
		stage.setTitle("学生信息管理系统-更改密码");
		stage.show();
	}

	@FXML
	public void exit(ActionEvent actionEvent) {
		System.exit(0);
	}
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Value.initForm(form, number, name, gender, classCol, studentNumber);
		if (Value.isAdmin){
			classCol.setCellFactory(TextFieldTableCell.forTableColumn());
			classCol.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
				Student student = t.getTableView().getItems().get(t.getTablePosition().getRow());
				student.setStudentClass(t.getNewValue());
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
		if (Value.isAdmin){
			for (Student s : Value.students){
				form.getItems().add(s);
			}
		}else {
			for (Student s : Value.students){
				if (s.studentNumber.equals(Value.studentAccount)){
					form.getItems().add(s);
					break;
				}
			}
		}
	}
	public void notAdmin(){
		delRow.setDisable(true);
		newRowBtn.setDisable(true);
		searchBtn.setDisable(true);
		changeAccountMenuItem.setDisable(true);
		importBtn.setDisable(true);
		exportBtn.setDisable(true);
		modeSwitcher.setDisable(true);
		StudentInformationManagementSystem.primaryStage.setTitle("学生信息管理系统-学生登录");
	}
	@FXML
	public void switchMode(ActionEvent actionEvent) {
		if (form.getSelectionModel().getSelectionMode().equals(SelectionMode.SINGLE)){
			delRow.setText("批量删除学生数据");
			modeSwitcher.setText("切换到单选模式");
			form.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}else {
			delRow.setText("删除选中学生数据");
			modeSwitcher.setText("切换到多选模式");
			form.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		}
		isSingleSelectionMode = !isSingleSelectionMode;
	}

	public void count(ActionEvent actionEvent) {
		if (Value.students.isEmpty()) return;
		FXMLLoader fxmlLoader = new FXMLLoader(StudentInformationManagementSystem.class
				.getResource("statistic-view.fxml"));
		Stage stage = getStageFromLoader(fxmlLoader, 800, 600);
		stage.setTitle("学生信息管理系统-统计信息");
		stage.show();
	}
}

package com.wdc.studentinformationmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Value {
	public static int adminAccount = hash("admin");
	public static int adminPassword = hash("123456");
	public static String studentAccount = "null";
	public static int studentPassword = hash("123456");
	public static Image icon = new Image(Objects.requireNonNull(StudentInformationManagementSystem.class
					.getResource("icon.png")).toString());
	public static boolean isAdmin = true;
	public static Vector<Student> students = new Vector<>();


	public static void initVars(){
		File dir = new File("data/");
		File adminAccountAndPasswordFile = new File("data/AAaP.txt");
		File studentsFile = new File("data/students.txt");

		try{
			if (!adminAccountAndPasswordFile.exists()){
				dir.mkdirs();
				adminAccountAndPasswordFile.createNewFile();
				studentsFile.createNewFile();
				FileWriter fw = new FileWriter(adminAccountAndPasswordFile);
				fw.write(hash("admin") + "-==-" + hash("123456"));
				fw.close();
			}else {
				FileReader fr = new FileReader(adminAccountAndPasswordFile);
				char[] buf = new char[128];
				int len = fr.read(buf);
				fr.close();
				String adminAccountAndPasswordString = new String(buf, 0, len);
				String[] adminAccountAndPassword = adminAccountAndPasswordString.split("-==-");
				adminAccount = Integer.parseInt(adminAccountAndPassword[0]);
				adminPassword = Integer.parseInt(adminAccountAndPassword[1]);

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(studentsFile),
						StandardCharsets.UTF_8));
				String line;
				while ((line = br.readLine()) != null){
					String[] properties = line.split(" ");
					if (properties.length <= 1){
						System.out.println("有一行数据不符合格式");
						continue;
					}
					// 学号 姓名 性别 班级
					students.add(new Student(properties[0], properties[1], properties[2], properties[3]));
				}
				br.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void saveVars(){
		File adminAccountAndPasswordFile = new File("data/AAaP.txt");
		File studentsFile = new File("data/students.txt");
		try {
			FileWriter fw = new FileWriter(adminAccountAndPasswordFile);
			fw.write(adminAccount + "-==-" + adminPassword);
			fw.close();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(studentsFile),
					StandardCharsets.UTF_8));
			for (Student s : students){
				// 学号 姓名 性别 班级
				bw.write("%s %s %s %s".formatted(s.studentNumber, s.name, s.gender, s.shift));
				bw.newLine();
				bw.flush();
			}
			bw.close();
			File studentAccountFile = new File("data/" + studentAccount + ".txt");
			if (studentAccount != null){
				FileWriter fileWriter = new FileWriter(studentAccountFile);
				fileWriter.write(String.valueOf(studentPassword));
				fileWriter.close();
			}
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	public static Alert createAlert(Alert.AlertType type, String title, String text){
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().setAll(icon);
		return alert;
	}
	public static void addStudent(Student s){
		students.add(s);
	}
	public static void initForm(TableView<Student> form, TableColumn<Student, String> number,
	                            TableColumn<Student, String> name, TableColumn<Student, String> gender,
	                            TableColumn<Student, String> classCol, TableColumn<Student, String> studentNumber){
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
	public static int hash(String s){
		return Objects.hash(s);
	}
}

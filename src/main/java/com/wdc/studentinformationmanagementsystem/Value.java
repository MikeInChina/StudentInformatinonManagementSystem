package com.wdc.studentinformationmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Vector;

public class Value {
	public static String adminAccount = "admin";
	public static String adminPassword = "123456";
	public static Image icon = new Image(Objects.requireNonNull(StudentInformationManagementSystem.class
					.getResource("icon.png")).toString());
	public static boolean isAdmin = true;
	public static Vector<Student> students = new Vector<>();


	public static void initVars(){
		File adminAccountAndPasswordFile = new File("data/AAaP.txt");
		File studentsFile = new File("data/students.txt");

		try{
			if (!adminAccountAndPasswordFile.exists()){
				adminAccountAndPasswordFile.createNewFile();
				studentsFile.createNewFile();
				FileWriter fw = new FileWriter(adminAccountAndPasswordFile);
				fw.write("admin-==-123456");
				fw.close();
			}else {
				FileReader fr = new FileReader(adminAccountAndPasswordFile);
				char[] buf = new char[128];
				int len = fr.read(buf);
				fr.close();
				String adminAccountAndPasswordString = new String(buf, 0, len);
				String[] adminAccountAndPassword = adminAccountAndPasswordString.split("-==-");
				adminAccount = adminAccountAndPassword[0];
				adminPassword = adminAccountAndPassword[1];

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(studentsFile),
						StandardCharsets.UTF_8));
				String line;
				while ((line = br.readLine()) != null){
					String[] properties = line.split(", ");
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
				bw.write("%s, %s, %s, %s".formatted(s.studentNumber, s.name, s.gender, s.shift));
				bw.newLine();
				bw.flush();
			}
			bw.close();
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
}

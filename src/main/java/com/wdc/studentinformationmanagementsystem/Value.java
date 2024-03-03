package com.wdc.studentinformationmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Value {
	public static String adminAccount = "admin";
	public static String adminPassword = "123456";
	public static Image icon = new Image(StudentInformationManagementSystem.class.getResource("icon.png").
			toString());
	public static boolean isAdmin = true;


	public static void initVars(){
		File adminAccountAndPasswordFile = new File("data/AAaP.txt");

		try{
			if (!adminAccountAndPasswordFile.exists()){
				adminAccountAndPasswordFile.createNewFile();
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
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void saveVars(){
		File adminAccountAndPasswordFile = new File("data/AAaP.txt");
		try {
			FileWriter fw = new FileWriter(adminAccountAndPasswordFile);
			fw.write(adminAccount + "-==-" + adminPassword);
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

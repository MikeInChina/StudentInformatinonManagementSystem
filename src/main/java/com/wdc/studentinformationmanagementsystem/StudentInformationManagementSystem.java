package com.wdc.studentinformationmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * 作者：王墩灿 <br>
 * 学校：广州开发区外国语学校 <br>
 * 班级：初二（1）班 <br>
 * 主程序，程序的入口。<br>
 * 登录界面控制器是LoginController
 * @see LoginController
 */
public class StudentInformationManagementSystem extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(StudentInformationManagementSystem
				.class.getResource("login-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 400, 200);
		Image image = new Image(StudentInformationManagementSystem.class.getResource("icon.png").toString());
		stage.setTitle("学生信息管理系统");
		stage.getIcons().setAll(image);

		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
package com.wdc.studentinformationmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static com.wdc.studentinformationmanagementsystem.Value.icon;

/**
 * 作者：王墩灿 <br>
 * 学校：广州开发区外国语学校 <br>
 * 班级：初二（1）班 <br>
 * 指导老师：陈桂桦老师 <br>
 * <br>
 * 主程序，程序的入口。
 */
public class StudentInformationManagementSystem extends Application {
	public static Stage primaryStage;
	public static MainController mainController;
	@Override
	public void start(Stage stage) throws IOException {
		StudentInformationManagementSystem.primaryStage = stage;


		FXMLLoader loginFXML = new FXMLLoader(StudentInformationManagementSystem
				.class.getResource("login-view.fxml"));
		Scene loginScene = new Scene(loginFXML.load(), 400, 200);
		stage.setScene(loginScene);

		stage.getIcons().setAll(icon);
		stage.setTitle("学生信息管理系统");
		stage.show();

		//全局变量初始化
		Value.initVars();

		//添加Shutdown Hook
		Runtime.getRuntime().addShutdownHook(new Hook());
	}
	public static void setMainScene(boolean isAdmin) throws IOException {
		Value.isAdmin = isAdmin;
		FXMLLoader mainFXML = new FXMLLoader(StudentInformationManagementSystem
				.class.getResource("main-view.fxml"));
		Scene main = new Scene(mainFXML.load(), 800, 600);
		primaryStage.setScene(main);
		mainController = mainFXML.getController();
		if (!isAdmin){
			mainController.notAdmin();
		}
		Screen screen = Screen.getPrimary();
		Rectangle2D rectangle2d = screen.getBounds();
		primaryStage.setX(rectangle2d.getWidth()/2 - 400);
		primaryStage.setY(rectangle2d.getHeight()/2 - 300);
	}
	/**
	 * Shutdown Hook
	 */
	private static class Hook extends Thread {
		@Override
		public void run() {
			Value.saveVars();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
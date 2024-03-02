package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * 主界面的控制器类，控制主界面的事件处理
 */
public class MainController {

	@FXML
	private Button delCol;
	@FXML
	private Button delRow;
	@FXML
	private Button filterBtn;
	@FXML
	private Button newColBtn;
	@FXML
	private Button newRowBtn;
	@FXML
	private Button searchBtn;
	@FXML
	public TableView<?> form;
	@FXML
	public TableColumn<?, ?> number;
	@FXML
	public TableColumn<?, ?> name;
	@FXML
	public TableColumn<?, ?> studentNumber;
	@FXML
	public TableColumn<?, ?> classCol;
	@FXML
	public TableColumn<?, ?> gender;
	public MainController(){
		form.setEditable(true);
		form.setPlaceholder(new Label("-"));
	}
	@FXML
	void createColumn(ActionEvent event) {

	}

	@FXML
	void createRow(ActionEvent event) {

	}

	@FXML
	void delColumn(ActionEvent event) {

	}

	@FXML
	void delRow(ActionEvent event) {

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
}

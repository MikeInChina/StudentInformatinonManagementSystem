package com.wdc.studentinformationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseColController {

    @FXML
    private ComboBox<String> classBox;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private ComboBox<String> nameBox;

    @FXML
    private ComboBox<String> numberBox;

	private ColsDataCallBack colsDataCallBack;

    public void setDataCallBack(ColsDataCallBack colsDataCallBack) {
        this.colsDataCallBack = colsDataCallBack;
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void ok(ActionEvent event) {
        if (classBox.getValue() == null || nameBox.getValue() == null || genderBox.getValue() == null
                || numberBox.getValue() == null){
            Alert alert = Value.createAlert(Alert.AlertType.CONFIRMATION,
                    "学生信息管理系统-提示", "有至少1列未选择，是否确认将未选择的列填充为空白？");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get() != ButtonType.OK) return;
        }
        int[] cols = new int[4];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher1 = pattern.matcher(numberBox.getValue()), matcher2 = pattern.matcher(nameBox.getValue()),
                matcher3 = pattern.matcher(genderBox.getValue()), matcher4 = pattern.matcher(classBox.getValue());
        if (matcher1.find()) cols[0] = Integer.parseInt(matcher1.group());
        else cols[0] = -1;
        if (matcher2.find()) cols[1] = Integer.parseInt(matcher2.group());
        else cols[1] = -1;
        if (matcher3.find()) cols[2] = Integer.parseInt(matcher3.group());
        else cols[2] = -1;
        if (matcher4.find()) cols[3] = Integer.parseInt(matcher4.group());
        else cols[3] = -1;
        colsDataCallBack.onColsDataReceived(cols);
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void setCols(String[] row){
	    for (int i = 0;i < row.length;i++){
            String item = "第 %d 列，列首数据“%s”".formatted(i+1, row[i]);
            classBox.getItems().add(item);
            nameBox.getItems().add(item);
            genderBox.getItems().add(item);
            numberBox.getItems().add(item);
        }
    }
}

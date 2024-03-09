package com.wdc.studentinformationmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatisticsController implements Initializable {
    @FXML
    private BarChart<String, Number> classChart;
    @FXML
    private CategoryAxis classChartCategory;
    @FXML
    private NumberAxis classChartNumber;
    @FXML
    private PieChart genderChart;
    @FXML
    private Label infoLabel;

    @FXML
    void quit(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int numOfMale = 0, numOfFemale = 0;
        for(Student s : Value.students){
            if (s.getGender().equals("男")) numOfMale++;
            else numOfFemale++;
        }
        double malePercentage = (double) numOfMale / (numOfMale + numOfFemale);
        double femalePercentage = 1.0 - malePercentage;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("男：%d人".formatted(numOfMale), numOfMale),
                new PieChart.Data("女：%d人".formatted(numOfFemale), numOfFemale)
        );
        genderChart.setStartAngle(270);
        genderChart.setData(pieChartData);
        infoLabel.setText("男：%.2f%%    女:%.2f%%".formatted(malePercentage * 100, femalePercentage*100));

        int[] grades = new int[32];
        int[] classes = new int[256];
        int[][] nums = new int[32][256];
        int maxNumber = -1;
        for(Student s : Value.students){
            String classStr = s.getStudentClass();
            Pattern pattern = Pattern.compile("(\\d+)年(\\d+)班");
            Matcher matcher = pattern.matcher(classStr);
            if (matcher.find()){
                int grade = Integer.parseInt(matcher.group(1)); // 提取年级信息
                int clazz = Integer.parseInt(matcher.group(2)); // 提取班级信息
                grades[grade]++;
                classes[clazz]++;
                nums[grade][clazz]++;
            }
        }
        for(int c = 0;c < classes.length;c++){
            if (classes[c] == 0) continue;
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("%d班".formatted(c));
            for(int g = 0;g < grades.length;g++){
                if (grades[g] == 0) continue;
                series.getData().add(new XYChart.Data<>("%d年级".formatted(g), nums[g][c]));
                maxNumber = Math.max(nums[g][c], maxNumber);
            }
            classChart.getData().add(series);
            for (XYChart.Data<String, Number> data : series.getData()) {
                Label text = new Label(data.getYValue().toString());
                StackPane stackPane = (StackPane)data.getNode();
                text.setTranslateY(-15);
                text.setFont(new Font(10));
                text.setTextFill(Color.gray(0.3));
                stackPane.getChildren().add(text);
                StackPane.setAlignment(text, Pos.TOP_CENTER);
            }
        }
        classChartNumber.setAutoRanging(false);
        classChartNumber.setUpperBound(maxNumber*1.1);
    }
}

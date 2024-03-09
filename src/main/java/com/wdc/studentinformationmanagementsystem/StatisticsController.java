package com.wdc.studentinformationmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private BarChart<String, Integer> classChart;
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
                new PieChart.Data("男", numOfMale),
                new PieChart.Data("女", numOfFemale)
        );
        genderChart.setStartAngle(270);
        genderChart.setData(pieChartData);
        infoLabel.setText("男：%.2f%%    女:%.2f%%".formatted(malePercentage * 100, femalePercentage*100));
    }
}

module com.wdc.studentinformationmanagementsystem {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.wdc.studentinformationmanagementsystem to javafx.fxml;
	exports com.wdc.studentinformationmanagementsystem;
}
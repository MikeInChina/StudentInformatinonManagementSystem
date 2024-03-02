module com.wdc.studentinformationmanagementsystem {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.dlsc.formsfx;
	requires org.kordamp.ikonli.javafx;

	opens com.wdc.studentinformationmanagementsystem to javafx.fxml;
	exports com.wdc.studentinformationmanagementsystem;
}
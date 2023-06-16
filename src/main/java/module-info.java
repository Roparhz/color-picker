module com.javacolor.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javacolor.demo to javafx.fxml;
    exports com.javacolor.demo;
    exports com.javacolor.demo.controllers;
    opens com.javacolor.demo.controllers to javafx.fxml;
}
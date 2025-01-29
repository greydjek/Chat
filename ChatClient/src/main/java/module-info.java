module ChatClient {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;
    requires org.apache.logging.log4j;
    opens app;
    opens NetWork;
    opens controllers;
}
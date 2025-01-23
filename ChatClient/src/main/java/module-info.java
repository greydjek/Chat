module ChatClient {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens app;
    opens NetWork;
    opens controllers;
}
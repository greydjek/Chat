package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class ExempleAplication extends Application {
    public static void  main(String[] args){
launch(args);
    }
    private static final Logger logger = LogManager.getLogger(ExempleAplication.class);
    private static final Marker marker1 = MarkerManager.getMarker("MARKER1");
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/ChatResurse.fxml"));
        Parent parent= loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setTitle("SingleChat");
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.info(marker1, "Мы только что поприветствовали пользователя!");

    }
}

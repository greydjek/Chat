package lesson3java2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExempleAplication extends Application {
    public static void  main(String[] args){
launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/ChatResurse.fxml"));
        Parent parent= loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setTitle("SingleChat");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

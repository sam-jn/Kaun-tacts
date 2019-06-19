package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("KAUNTACTS HOME");
        primaryStage.setScene(new Scene(root, 890 , 670));
        primaryStage.show();
//        Controller.fillList();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

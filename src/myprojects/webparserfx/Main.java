package myprojects.webparserfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("webparser.fxml"));
        Parent root = loader.load();

        Controller myController = loader.getController();
//        Parent root = FXMLLoader.load(getClass().getResource("webparser.fxml"));

        primaryStage.setTitle("Web Parser");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();

        // This should be executed everytime there is a change on choicebox
//        String url = myController.getUrl(myController.getJobTypeMenu());

    }


    public static void main(String[] args) {
        launch(args);
    }
}

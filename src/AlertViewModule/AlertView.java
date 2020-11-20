package AlertViewModule;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class AlertView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Уведомление");
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }

    public void start(Stage primaryStage, String alertText) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertView.fxml"));
        Parent root = loader.load();
        AlertViewController controller = loader.getController();
        controller.setAlertText(alertText);
        primaryStage.setTitle("Уведомление");
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

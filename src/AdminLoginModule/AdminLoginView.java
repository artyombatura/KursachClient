package AdminLoginModule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminLoginView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        primaryStage.setTitle("Вход админа");
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package AdminCapabilitiesModule;

import AlertViewModule.AlertViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddEmployeeView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        primaryStage.setTitle("Добавление нового сотрудника");
        primaryStage.setScene(new Scene(root, 200, 220));
        primaryStage.show();
    }

    public void start(Stage primaryStage, AddEmployeeDelegate delegate) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));
        Parent root = loader.load();
        AddEmployeeController controller = loader.getController();
        controller.delegate = delegate;
        primaryStage.setTitle("Добавление нового сотрудника");
        primaryStage.setScene(new Scene(root, 200, 220));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
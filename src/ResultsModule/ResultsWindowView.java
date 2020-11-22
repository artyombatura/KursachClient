package ResultsModule;

import AlertViewModule.AlertViewController;
import Models.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ResultsWindowView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultsView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Расчитанная зарплата");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public void start(Stage primaryStage, ArrayList<Employee> employees) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultsView.fxml"));
        Parent root = loader.load();
        ResultsWindowController controller = loader.getController();
        controller.fillEmployeesDataSource(employees);
        primaryStage.setTitle("Расчитанная зарплата");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }
}

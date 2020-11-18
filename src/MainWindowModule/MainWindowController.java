package MainWindowModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import AdminLoginModule.AdminLoginView;
import AdminLoginModule.AdminLoginController;
import AdminLoginModule.*;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private TableView<?> tableView;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Button countSelectedButton;

    @FXML
    private Button countAllButton;

    @FXML
    private Button adminPanelButton;

    @FXML
    void adminPanelButtonAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            AdminLoginView adminLoginView = new AdminLoginView();
            adminLoginView.start(stage);
        } catch (Exception e) {
            System.out.println("Cannot open admin login window.\nWith exception" + e.getLocalizedMessage() );
        }
    }

    @FXML
    void countAllButtonAction(ActionEvent event) {
        System.out.println("count all");
    }

    @FXML
    void countSelectedButtonAction(ActionEvent event) {
        System.out.println("count selected");
    }

    @FXML
    void fromDatePickerAction(ActionEvent event) {

    }

    @FXML
    void toDatePickerAction(ActionEvent event) {

    }

}

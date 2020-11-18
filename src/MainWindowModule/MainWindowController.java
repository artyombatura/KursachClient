package MainWindowModule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

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
        System.out.println("open admin panel");
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

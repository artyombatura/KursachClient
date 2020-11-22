package AdminCapabilitiesModule;

import Models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable, AddEmployeeDelegate {

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> patronymicColumn;

    @FXML
    private TableColumn<Employee, Integer> salaryByContractColumn;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button deleteSelectedEmployee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addEmployeeAction(ActionEvent event) {
        showEmployeeAddingMenu();
    }

    @FXML
    void deleteSelectedEmployeeAction(ActionEvent event) {

    }

    // Show methods
    private void showEmployeeAddingMenu() {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            AddEmployeeView adminPanel = new AddEmployeeView();
            adminPanel.start(stage, this::successfullyAdded);
        } catch (Exception e) {
            System.out.println("Cannot open adding employee menu.");
        }
    }

    // Adding delegate
    @Override
    public void successfullyAdded(Employee employee) {
        System.out.println("New employee added, info\n" + employee.getLastName());
    }
}

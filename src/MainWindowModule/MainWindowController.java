package MainWindowModule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import AdminLoginModule.AdminLoginView;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Employee;

public class MainWindowController implements Initializable {

    @FXML
    TableColumn<Employee, String> lastNameColumn;

    @FXML
    TableColumn<Employee, String> firstNameColumn;

    @FXML
    TableColumn<Employee, String> patronymicColumn;

    @FXML
    TableColumn<Employee, Integer> salaryByContractColumn;

    @FXML
    TableColumn<Employee, TextField> hoursWorkedColumn;

    @FXML
    TableColumn<Employee, TextField> workRateColumn;

    @FXML
    private TableView<Employee> tableView;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        salaryByContractColumn.setCellValueFactory(new PropertyValueFactory<>("SalaryByContract"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("HoursWorkedTextField"));
        workRateColumn.setCellValueFactory(new PropertyValueFactory<>("WorkRateTextField"));

        tableView.setItems(employeesDataSource);
    }

    private ObservableList<Employee> employeesDataSource = FXCollections.observableArrayList (
            new Employee("Артем", "Батура", "Геннадьевич", 3500),
            new Employee("Константин", "Петрикевич", "...славович???", 4000)
    );

    @FXML
    void adminPanelButtonAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
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
        TableView.TableViewSelectionModel<Employee> seletectedModel = tableView.getSelectionModel();
        Employee selectedEmployee = seletectedModel.getSelectedItem();
        System.out.println(selectedEmployee.toString());
    }

    @FXML
    void fromDatePickerAction(ActionEvent event) {

    }

    @FXML
    void toDatePickerAction(ActionEvent event) {

    }

}

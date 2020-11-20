package MainWindowModule;

import AlertViewModule.AlertViewController;
import Helpers.DateHelpers.DateHelpers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import AdminLoginModule.AdminLoginView;
import AlertViewModule.AlertView;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Models.Employee;

public class MainWindowController implements Initializable {

    // Subviews
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

    // Variables
    LocalDate fromDate;
    LocalDate toDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            fromDate = newValue;
            System.out.println(newValue);
            if (isDatePickerBothFilled()) {
                Integer workRate = DateHelpers.getWorkHoursFromDateRange(fromDate, toDate);
                updateModelsWorkRate(workRate);
            }
        });

        toDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            toDate = newValue;
            System.out.println(newValue);
            if (isDatePickerBothFilled()) {
                Integer workRate = DateHelpers.getWorkHoursFromDateRange(fromDate, toDate);
                updateModelsWorkRate(workRate);
            }
        });

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
    void countAllButtonAction(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            AlertView alertView = new AlertView();
            alertView.start(stage, "Моя тестовая ошибка");
        } catch (Exception e) {
            System.out.println("Cannot open alert view.\nWith exception" + e.getLocalizedMessage() );
        }
    }

    @FXML
    void countSelectedButtonAction(ActionEvent event) {
        TableView.TableViewSelectionModel<Employee> seletectedModel = tableView.getSelectionModel();
        Employee selectedEmployee = seletectedModel.getSelectedItem();

        if (selectedEmployee.getHoursWorkedTextField().getText() != "" &&
            selectedEmployee.getWorkRateTextField().getText() != "") {
            System.out.println(selectedEmployee.toString());
        } else {
            showAlert("Все поля должны быть заполнены!");
        }
    }

    @FXML
    void fromDatePickerAction(ActionEvent event) {

    }

    @FXML
    void toDatePickerAction(ActionEvent event) {

    }

    // Private method ths shows alert view with custom user error
    private void showAlert(String withText) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            AlertView alertView = new AlertView();
            alertView.start(stage, withText);
        } catch (Exception e) {
            System.out.println("Cannot open alert view.\nWith exception" + e.getLocalizedMessage() );
        }
    }

    // Date pickers logic
    private Boolean isDatePickerBothFilled() {
        if (fromDate == null || toDate == null) { return false; }
        return true;
    }

    private void updateModelsWorkRate(Integer newWorkRate) {
        for (int i = 0; i < employeesDataSource.size(); i++) {
            Employee employee = employeesDataSource.get(i);
            String workRateString = newWorkRate.toString();
            employee.getWorkRateTextField().setText(workRateString);
        }
    }
}

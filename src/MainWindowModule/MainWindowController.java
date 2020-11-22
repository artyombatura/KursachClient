package MainWindowModule;

import ConnectToServer.Client;
import Constants.Constants;
import Helpers.DateHelpers.DateHelpers;
import ResultsModule.ResultsWindowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import AdminLoginModule.AdminLoginView;
import AlertViewModule.AlertView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

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
    private ObservableList<Employee> employeesDataSource = FXCollections.observableArrayList ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            fromDate = newValue;
            System.out.println(newValue);
            if (isDatePickerBothFilled()) {
                Integer workRate = DateHelpers.getWorkHoursFromDateRange(fromDate, toDate);
                updateModelsWorkRate(workRate);

                countSelectedButton.setDisable(false);
                countAllButton.setDisable(false);
            }
        });

        toDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            toDate = newValue;
            System.out.println(newValue);
            if (isDatePickerBothFilled()) {
                Integer workRate = DateHelpers.getWorkHoursFromDateRange(fromDate, toDate);
                updateModelsWorkRate(workRate);

                countSelectedButton.setDisable(false);
                countAllButton.setDisable(false);
            }
        });

        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        salaryByContractColumn.setCellValueFactory(new PropertyValueFactory<>("SalaryByContract"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("HoursWorkedTextField"));
        workRateColumn.setCellValueFactory(new PropertyValueFactory<>("WorkRateTextField"));

        try {
            employeesDataSource.addAll(Constants.clientConnect.showAllContribution());
           if(employeesDataSource.size()!=0) {
               tableView.setItems(employeesDataSource);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }


        countAllButton.setDisable(true);
        countSelectedButton.setDisable(true);
    }

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
        AtomicReference<Boolean> shouldReturn = new AtomicReference<>(false);
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employeesDataSource.forEach((employee -> {
            if (shouldReturn.get()) { return; }

            if (employee.getHoursWorkedTextField().getText() != "" &&
                    employee.getWorkRateTextField().getText() != "") {
                // Fake salary count
                employee.setCountedSalary(1000);
                employees.add(employee);
            } else {
                showAlert("Все поля должны быть заполнены!");
                shouldReturn.set(true);
            }
        }));

        if (shouldReturn.get()) { return; }
        else { showResultsWindow(employees); }
    }

    @FXML
    void countSelectedButtonAction(ActionEvent event) {
        TableView.TableViewSelectionModel<Employee> seletectedModel = tableView.getSelectionModel();
        Employee selectedEmployee = seletectedModel.getSelectedItem();
        System.out.println(selectedEmployee.toString());
        //  Fake salary count
        selectedEmployee.setCountedSalary(1000);

        if (selectedEmployee.getHoursWorkedTextField().getText() != "" &&
            selectedEmployee.getWorkRateTextField().getText() != "") {
            ArrayList<Employee> employeesArrayList = new ArrayList<Employee>();
            employeesArrayList.add(selectedEmployee);
            showResultsWindow(employeesArrayList);
        } else {
            showAlert("Все поля должны быть заполнены!");
        }
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

    // Shows results window
    private void showResultsWindow(ArrayList<Employee> employeesToDisplay) {
        try {
            Stage stage = new Stage();
            ResultsWindowView resultsWindowView = new ResultsWindowView();
            resultsWindowView.start(stage, employeesToDisplay);
        } catch (Exception e) {
            System.out.println("Cannot open results window.\nWith exception" + e.getLocalizedMessage() );
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

package ResultsModule;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultsWindowController implements Initializable {

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> patronymicColumn;

    @FXML
    private TableColumn<Employee, Integer> salaryColumn;

    // Properties
    private ObservableList<Employee> employeesDataSource = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("CountedSalary"));

        tableView.setItems(employeesDataSource);
    }

    // Public methods
    // Fill employeesList
    public void fillEmployeesDataSource(ArrayList<Employee> employees) {
        employees.forEach((employee -> {
            employeesDataSource.add(employee);
        }));
    }
}

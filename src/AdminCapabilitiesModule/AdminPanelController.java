package AdminCapabilitiesModule;

import ConnectToServer.Client;
import Constants.Constants;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private Button updateData;


    @FXML
    private TableView<Employee> workers;

    private ObservableList<Employee> employeesDataSource = FXCollections.observableArrayList ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        updateData.setOnAction(event -> {
            initTable();
        });
    }

    private void initTable(){
        employeesDataSource.clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        salaryByContractColumn.setCellValueFactory(new PropertyValueFactory<>("SalaryByContract"));
        try {
            employeesDataSource.addAll(Client.interactionsWithServer.showAllContribution());
        } catch (IOException e) {
            e.printStackTrace();
        }
        workers.setItems(employeesDataSource);
    }

    @FXML
    void addEmployeeAction(ActionEvent event) {
        showEmployeeAddingMenu();
        initTable();
    }

    @FXML
    void deleteSelectedEmployeeAction(ActionEvent event) {
        int  count = workers.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.deleteWorker(employeesDataSource.get(count).getId());
        initTable();
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

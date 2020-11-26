package AdminCapabilitiesModule;

import ConnectToServer.Client;
import Constants.Constants;
import Models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddEmployeeController {

    public AddEmployeeDelegate delegate;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Button addButton;

    @FXML
    void addAction(ActionEvent event) {
        Client.interactionsWithServer.addWorker(lastNameTextField.getText(),firstNameTextField.getText(),patronymicTextField.getText(),salaryTextField.getText());
    }
}

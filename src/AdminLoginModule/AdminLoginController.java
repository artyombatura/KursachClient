package AdminLoginModule;

import AdminCapabilitiesModule.AdminPanelView;
import ConnectToServer.Client;
import Constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    void loginButtonAction(ActionEvent event) {
        try {
            if(Client.interactionsWithServer.checkAdminAccount(loginTextField.getText(),passwordTextField.getText())){
                openAdminPanel();

                // Closing authorization window (this)
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            }
            else{
                System.out.println("Неверный логин или пароль !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void openAdminPanel() {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            AdminPanelView adminPanel = new AdminPanelView();
            adminPanel.start(stage);
        } catch (Exception e) {
            System.out.println("Cannot open admin panel.");
        }
    }
}

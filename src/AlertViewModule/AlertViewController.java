package AlertViewModule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

public class AlertViewController implements Initializable {

    @FXML
    private Label alertLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAlertText(String withText) {
        this.alertLabel.setText(withText);
    }
}

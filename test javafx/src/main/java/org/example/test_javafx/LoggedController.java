package org.example.test_javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedController implements Initializable {

    @FXML
    private Button button_login;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_signup;
    @FXML
    Label label_welcome;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "hello-view.fxml", "Log in", null);
            }
        });
    }

    public void setUserInformation(String username) {
        label_welcome.setText("Siema " + username);
    }
}

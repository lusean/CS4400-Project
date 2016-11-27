package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField usernameField, emailField;

    @FXML
    private PasswordField passwordField, confirmField;

    @FXML
    private Button backButton, createButton;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }

    @FXML
    private void handleCreatePressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }

}

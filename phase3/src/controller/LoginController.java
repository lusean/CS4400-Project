package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton, registerButton;

    @FXML
    private void handleLoginPressed() {
        //MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }

    @FXML
    private void handleRegisterPressed() {
        MainController.getInstance().changeScene("../view/RegistrationScreen.fxml", "New Student Registration");
    }
}

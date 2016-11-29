package controller;

import entity.Student;
import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton, registerButton;

    @FXML
    private void handleLoginPressed() throws SQLException {

        /* get list of students and admins
            check if username and password matches
            if matches, then get the user from the list and store it in MainController for later user

         */

        if(isLoginValid() == 1) {
            Student student = null;
            for(Student s : Student.selectAllStudents()) {
                if(s.username.equals(usernameField.getText())) {
                    student = s;
                }
            }
            MainController.getInstance().setStudent(student);
            MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
        } else if (isLoginValid() == 2) {
            MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
        } else {
            MainController.getInstance().showAlertMessage("Incorrect username and/or password");
        }
    }

    /* if login matches and user is admin, then return 2
        if user is student, then return 1
        ele if username or password does not match, return 0
     */

    private int isLoginValid() throws SQLException {
        for(User u : User.selectAllUsers()) {
            if(u.username.equals(usernameField.getText()) && u.password.equals(passwordField.getText())) {
                if(u.isAdmin) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    @FXML
    private void handleRegisterPressed() {
        MainController.getInstance().changeScene("../view/RegistrationScreen.fxml", "New Student Registration");
    }
}

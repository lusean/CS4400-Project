package controller;

import entity.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

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
        if(isStudentValid()) {
            Student student = new Student(usernameField.getText(), emailField.getText(), null, null);
            try {
                student.insert();
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage(e.getMessage());
            }
            MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
        }
    }

    private boolean isStudentValid() {
        try {
            List<Student> students = Student.selectAllStudents();
            for(Student s : students) {
                if(s.username.equals(usernameField.getText())) {
                    return false;
                }
            }
        } catch (SQLException e) {
            MainController.getInstance().showAlertMessage(e.getMessage());
        }

        return true;
    }
}

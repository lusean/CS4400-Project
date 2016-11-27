package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddProjectController {

    @FXML
    private Button backButton, submitButton;

    @FXML
    private TextField nameField, advisorField, emailField, descriptionField, studentField;

    @FXML
    private ComboBox categoryBox, designationBox, majorBox, yearBox, departmentBox;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void handleSubmitPressed() {
        MainController.getInstance().showOKMessage("Project Successfully Added.");
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddCourseController {

    @FXML
    private Button backButton, submitButton, addButton, removeButton;

    @FXML
    private TextField numberField, nameField, instructorField, studentField;

    @FXML
    private ComboBox categoryBox, designationBox;

    @FXML
    private ListView categoryList;

    @FXML
    private void handleAddCategoryPressed() {

    }

    @FXML
    private void handleRemoveCategoryPressed() {

    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void handleSubmitPressed() {
        MainController.getInstance().showOKMessage("Course Successfully Added.");
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }
}

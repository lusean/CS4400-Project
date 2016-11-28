package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DetailsController {

    @FXML
    private TextField titleField, descriptionField;

    @FXML
    private Button backButton, applyButton;

    private boolean isCourse;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }

    @FXML
    private void handleApplyPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }
}


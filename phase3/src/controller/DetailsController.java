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
    private void initialize() {
        if(MainController.getInstance().isCourse()) {
            applyButton.setVisible(false);
        }
    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }

    @FXML
    private void handleApplyPressed() {
        if(MainController.getInstance().isProfileUpdated()) {
            MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
        } else {
            MainController.getInstance().showAlertMessage("Please update your profile to include a year and major");
        }

    }
}


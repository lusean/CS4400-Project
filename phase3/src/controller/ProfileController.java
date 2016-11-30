package controller;

import entity.Major;
import entity.Year;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ProfileController {

    @FXML
    private Button backButton, updateButton;

    @FXML
    private TextField departmentField;

    @FXML
    private ComboBox majorBox, yearBox;

    @FXML
    private void initialize() throws SQLException {
        for(Major m : Major.selectAllMajors()) {
            majorBox.getItems().add(m.name);
        }
        for(Year y : Year.selectAllYears()) {
            yearBox.getItems().add(y.name);
        }
    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }

    @FXML
    private void handleUpdatePressed() {
        MainController.getInstance().showOKMessage("Your profile has been updated.");
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }
}

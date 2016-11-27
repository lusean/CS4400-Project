package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class MeController {

    @FXML
    private Button backButton;

    @FXML
    private Hyperlink profileLink, applicationLink;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }

    @FXML
    private void handleProfilePressed() {
        MainController.getInstance().changeScene("../view/ProfileScreen.fxml", "Edit Profile");
    }

    @FXML
    private void handleApplicationPressed() {
        MainController.getInstance().changeScene("../view/MyApplicationScreen.fxml", "My Application");
    }
}

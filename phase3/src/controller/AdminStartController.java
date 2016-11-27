package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class AdminStartController {

    @FXML
    private Hyperlink applicationLink, projectLink, reportLink, addProjectLink, addCourseLink;

    @FXML
    private Button logoutButton;

    @FXML
    private void handleViewApplicationsPressed() {
        MainController.getInstance().changeScene("../view/ApplicationScreen.fxml", "Application");
    }

    @FXML
    private void handleViewProjectsPressed() {
        MainController.getInstance().changeScene("../view/PopularProjectScreen.fxml", "Popular Project");
    }

    @FXML
    private void handleViewAppReportsPressed() {
        MainController.getInstance().changeScene("../view/PopularProjectScreen.fxml", "Application Report");
    }

    @FXML
    private void handleAddProjectPressed() {
        MainController.getInstance().changeScene("../view/AddProjectScreen.fxml", "Add a Project");
    }

    @FXML
    private void handleAddCoursePressed() {
        MainController.getInstance().changeScene("../view/AddCourseScreen.fxml", "Add a Course");
    }

    @FXML
    private void handleLogoutPressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }
}

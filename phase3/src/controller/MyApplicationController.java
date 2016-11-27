package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyApplicationController {

    @FXML
    private Button backButton;

    @FXML
    private TableView table;

    @FXML
    private TableColumn dateCol, projectCol, statusCol;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }
}

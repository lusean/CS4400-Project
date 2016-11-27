package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PopularProjectController {

    @FXML
    private TableView table;

    @FXML
    private TableColumn projectCol, numberCol;

    @FXML
    private Button backButton;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }
}



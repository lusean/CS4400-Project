package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ApplicationReportController {

    @FXML
    private TableView table;

    @FXML
    private TableColumn projectCol, numberCol, rateCol, majorCol;

    @FXML
    private Button backButton;

    @FXML
    private TextField totalField, acceptedField;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }
}

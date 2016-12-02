package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ObservableList data = FXCollections.observableArrayList();

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void initialize() {

    }
}

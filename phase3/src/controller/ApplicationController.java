package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ApplicationController {

    @FXML
    private TableView table;

    @FXML
    private TableColumn projectCol, majorCol, yearCol, statusCol;

    @FXML
    private Button rejectButton, acceptButton, backButton;

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void handleRejectPressed() {

    }

    @FXML
    private void handleAcceptPressed() {

    }

    private void updateTable() {

    }
}

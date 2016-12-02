package controller;

import entity.AdminViewApplication;
import entity.PopularProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ApplicationController {

    @FXML
    private TableView<AdminViewApplication> table;

    @FXML
    private TableColumn projectCol, majorCol, yearCol, statusCol;

    @FXML
    private Button rejectButton, acceptButton, backButton;

    private ObservableList data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        projectCol.setCellValueFactory(new PropertyValueFactory<AdminViewApplication, String>("projectName"));
        majorCol.setCellValueFactory(new PropertyValueFactory<AdminViewApplication, String>("studentMajor"));
        yearCol.setCellValueFactory(new PropertyValueFactory<AdminViewApplication, String>("studentYear"));
        statusCol.setCellValueFactory(new PropertyValueFactory<AdminViewApplication, String>("applyStatus"));
        try {
            for (AdminViewApplication ava : AdminViewApplication.selectAllAdminViewApplications()) {
                data.add(ava);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error building data");
        }
        table.getItems().setAll(data);
    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void handleRejectPressed() {
        AdminViewApplication ava = table.getSelectionModel().getSelectedItem();
        try {
            if (ava.getApplyStatus().equalsIgnoreCase("Pending")) {
                ava.updateApplyStatus("Rejected");
                initialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    @FXML
    private void handleAcceptPressed() {
        AdminViewApplication ava = table.getSelectionModel().getSelectedItem();
        try {
            if (ava.getApplyStatus().equalsIgnoreCase("Pending")) {
                ava.updateApplyStatus("Accepted");
                initialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {

    }
}

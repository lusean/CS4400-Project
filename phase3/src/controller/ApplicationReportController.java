package controller;

import entity.ApplicationReport;
import entity.Student;
import entity.StudentProjectApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

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
        projectCol.setCellValueFactory(new PropertyValueFactory<ApplicationReport, String>("projectName"));
        numberCol.setCellValueFactory(new PropertyValueFactory<ApplicationReport, Integer>("numApplicants"));
        rateCol.setCellValueFactory(new PropertyValueFactory<ApplicationReport, Double>("acceptRate"));
        majorCol.setCellValueFactory(new PropertyValueFactory<ApplicationReport, String>("topMajors"));
        try {
            for (ApplicationReport ar : ApplicationReport.getApplicationReports()) {
                data.add(ar);
            }
            totalField.setText(Integer.toString(StudentProjectApplication.getTotalApplications()));
            acceptedField.setText(Integer.toString(StudentProjectApplication.getAcceptedApplications()));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error building data");
        }
        table.getItems().setAll(data);
    }
}

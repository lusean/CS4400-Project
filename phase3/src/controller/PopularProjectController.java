package controller;

import entity.PopularProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class PopularProjectController {

    @FXML
    private TableView table;

    @FXML
    private TableColumn projectCol, numberCol;

    @FXML
    private Button backButton;

    private ObservableList data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        projectCol.setCellValueFactory(new PropertyValueFactory<PopularProject, String>("project"));
        numberCol.setCellValueFactory(new PropertyValueFactory<PopularProject, Integer>("numApplicants"));
        try {
            for (PopularProject p : PopularProject.selectPopularProjects()) {
                data.add(p);
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
}



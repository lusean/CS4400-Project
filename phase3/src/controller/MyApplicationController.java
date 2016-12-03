package controller;

import entity.PopularProject;
import entity.Student;
import entity.StudentProjectApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class MyApplicationController {

    @FXML
    private Button backButton;

    @FXML
    private TableView table;

    @FXML
    private TableColumn dateCol, projectCol, statusCol;

    private ObservableList data = FXCollections.observableArrayList();

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }

    @FXML
    private void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<StudentProjectApplication, String>("applyDate"));
        projectCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("project"));
        statusCol.setCellValueFactory(new PropertyValueFactory<StudentProjectApplication, String>("applyStatus"));
        try {
            String stu = MainController.getInstance().getStudent().username;
            for (StudentProjectApplication spa : StudentProjectApplication.selectStudentProjectApplicationsForStudent(stu)) {
                data.add(spa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error building data");
        }
        table.getItems().setAll(data);
    }
}

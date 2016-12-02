package controller;

import entity.Major;
import entity.Student;
import entity.Year;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileController {
    
    @FXML
    private Button backButton, updateButton;
    
    @FXML
    private TextField departmentField;
    
    @FXML
    private ComboBox<String> majorBox, yearBox;
    
    private Student student;
    
    @FXML
    private void initialize() throws SQLException {
        student = MainController.getInstance().getStudent();
        
        for (Major m : Major.selectAllMajors()) {
            majorBox.getItems().add(m.name);
        }
        for (Year y : Year.selectAllYears()) {
            yearBox.getItems().add(y.name);
        }
        
        majorBox.setValue(student.major);
        yearBox.setValue(student.year);
        
        majorBox.setOnAction(event -> updateDepartment());
        
        updateDepartment();
    }
    
    
    // need to tie departmentField to majorBox
    
    
    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }
    
    @FXML
    private void handleUpdatePressed() {
        try {
            student.updateMajor(majorBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            MainController.getInstance().showAlertMessage(e.getMessage());
        }
        try {
            student.updateYear(yearBox.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            MainController.getInstance().showAlertMessage(e.getMessage());
        }
        MainController.getInstance().showOKMessage("Your profile has been updated.");
        
        updateDepartment();
        
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
    }
    
    private void updateDepartment() {
        try {
            List<Major> majors = Major.selectMajor(majorBox.getSelectionModel().getSelectedItem());
            if (majors.size() == 1) {
                departmentField.setText(majors.get(0).department);
            }
        } catch (SQLException ignored) {}
    }
}

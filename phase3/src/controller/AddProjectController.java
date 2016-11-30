package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.DepthTest;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddProjectController {

    @FXML
    private Button backButton, submitButton, addButton, removeButton;

    @FXML
    private TextField nameField, advisorField, emailField, descriptionField, studentField;

    @FXML
    private ComboBox categoryBox, designationBox, majorBox, yearBox, departmentBox;

    @FXML
    private ListView categoryList;

    private ObservableSet categories = FXCollections.observableSet();

    @FXML
    private void initialize() throws SQLException {
        for(Category c : Category.selectAllCategory()) {
            categoryBox.getItems().add(c.name);
        }
        for(Designation d : Designation.selectAllDesignation()) {
            designationBox.getItems().add(d.name);
        }
        for(Major m : Major.selectAllMajors()) {
            majorBox.getItems().add(m.name);
        }
        for(Year y : Year.selectAllYears()) {
            yearBox.getItems().add(y.name);
        }
        for(Department d : Department.selectAllDepartments()) {
            departmentBox.getItems().add(d.name);
        }
    }

    @FXML
    private void handleAddCategoryPressed() {
        categories.add(categoryBox.getValue());
        categoryList.setItems(FXCollections.observableArrayList(categories));
    }

    @FXML
    private void handleRemoveCategoryPressed() {
        categories.remove(categoryList.getSelectionModel().getSelectedItem());
        categoryList.setItems(FXCollections.observableArrayList(categories));
    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }

    @FXML
    private void handleSubmitPressed() {
        MainController.getInstance().showOKMessage("Project Successfully Added.");
        MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
    }
}

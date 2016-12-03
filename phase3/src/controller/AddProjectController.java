package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.DepthTest;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AddProjectController {

    @FXML
    private Button backButton, submitButton, addButton, removeButton;

    @FXML
    private TextField nameField, advisorField, emailField, studentField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<String> categoryBox, designationBox, majorBox, yearBox, departmentBox;

    @FXML
    private ListView<String> categoryList;

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
        if(areFieldsFilled()) {
            if(studentField.getText().matches("\\d+")) {
                Project project = new Project(nameField.getText(), advisorField.getText(), emailField.getText(),
                        Integer.parseInt(studentField.getText()), descriptionField.getText(),
                        designationBox.getSelectionModel().getSelectedItem(), majorBox.getSelectionModel().getSelectedItem(),
                        yearBox.getSelectionModel().getSelectedItem(), departmentBox.getSelectionModel().getSelectedItem());
                try {
                    project.insert();
                    MainController.getInstance().showOKMessage("Project Successfully Added.");
                    MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
                } catch (SQLException e) {
                    MainController.getInstance().showAlertMessage(e.getMessage());
                }
                ProjectCategory projectCategory;
                for (String s : categoryList.getItems()) {
                    projectCategory = new ProjectCategory(nameField.getText(), s);
                    try {
                        projectCategory.insert();
                    } catch (SQLException e) {
                        MainController.getInstance().showAlertMessage(e.getMessage());
                    }
                }
            } else {
                MainController.getInstance().showAlertMessage("Please enter an integer number");
            }
        } else {
            MainController.getInstance().showAlertMessage("Please fill out all of the fields");
        }

    }

    private boolean areFieldsFilled() {
        return (nameField.getText().length() != 0 && advisorField.getText().length() != 0
                && emailField.getText().length() != 0 && descriptionField.getText().length() != 0
                && studentField.getText().length() != 0 && designationBox.getValue() != null
                && !categoryList.getItems().isEmpty());
    }
}

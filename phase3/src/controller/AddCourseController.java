package controller;

import entity.Category;
import entity.Course;
import entity.CourseCategory;
import entity.Designation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddCourseController {

    @FXML
    private Button backButton, submitButton, addButton, removeButton;

    @FXML
    private TextField numberField, nameField, instructorField, studentField;

    @FXML
    private ComboBox<String> categoryBox, designationBox;

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
                Course course = new Course(numberField.getText(), nameField.getText(), instructorField.getText(),
                        Integer.parseInt(studentField.getText()), designationBox.getSelectionModel().getSelectedItem());

                try {
                    course.insert();
                    MainController.getInstance().showOKMessage("Course Successfully Added.");
                    MainController.getInstance().changeScene("../view/AdminStartScreen.fxml", "Choose Functionality");
                } catch (SQLException e) {
                    MainController.getInstance().showAlertMessage(e.getMessage());
                }

                CourseCategory courseCategory;
                for (String s : categoryList.getItems()) {
                    courseCategory = new CourseCategory(numberField.getText(), s);
                    try {
                        courseCategory.insert();
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
        return (numberField.getText().length() != 0 && nameField.getText().length() != 0
                && instructorField.getText().length() != 0 && studentField.getText().length() != 0
                && designationBox.getValue() != null && !categoryList.getItems().isEmpty());
    }
}

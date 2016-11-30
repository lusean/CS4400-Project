package controller;

import entity.Category;
import entity.Designation;
import entity.Major;
import entity.Year;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class FilterController {

    @FXML
    private Button meButton, filterButton, resetButton, addButton, removeButton, viewButton, logoutButton;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> designationBox, majorBox, yearBox, categoryBox, typeBox;

    @FXML
    private TableView table;

    @FXML
    private TableColumn nameCol, typeCol;

    @FXML
    private ListView categoryList;

    private ObservableSet categories = FXCollections.observableSet();

    @FXML
    private void initialize() throws SQLException {
        typeBox.getItems().addAll("Project", "Course", "Both");
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
    }

    @FXML
    private void handleMePressed() {
        MainController.getInstance().changeScene("../view/MeScreen.fxml", "Me");
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
    private void handleFilterPressed() {

    }

    @FXML
    private void handleResetPressed() {

    }

    @FXML
    private void handleViewPressed() {
        MainController.getInstance().changeScene("../view/DetailsScreen.fxml", "Details");
    }

    @FXML
    private void handleLogoutPressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }
}

package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterController {

    @FXML
    private Button meButton, filterButton, resetButton, addButton, removeButton, viewButton, logoutButton;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> designationBox, majorBox, yearBox, categoryBox, typeBox;

    @FXML
    private TableView<SearchProjectsCourses> table;

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

        nameCol.setCellFactory(new PropertyValueFactory<SearchProjectsCourses, String>("name"));
        typeCol.setCellFactory(new PropertyValueFactory<SearchProjectsCourses, String>("isProject"));
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
        ObservableList filterData = FXCollections.observableArrayList();
        try {
            List<SearchProjectsCourses> list = SearchProjectsCourses.selectAllProjectsAndCourses(titleField.getText(),
                    categoryList.getItems(), designationBox.getSelectionModel().getSelectedItem(),
                    majorBox.getSelectionModel().getSelectedItem(), yearBox.getSelectionModel().getSelectedItem());
            for (SearchProjectsCourses s : list) {
                filterData.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error building data");
        }

        table.getItems().setAll(filterData);
    }

    @FXML
    private void handleResetPressed() {
        table.getItems().clear();
    }

    @FXML
    private void handleViewPressed() {
        // get the type from the selected entry
        // if it is a course, set the isCourse variable in MainController to true
        // also set the course or project variable in MainController
        SearchProjectsCourses selected = table.getSelectionModel().getSelectedItem();
        try {
            if (selected.isProject) {
                MainController.getInstance().setIsCourse(false);
                MainController.getInstance().setProject(Project.getProject(selected.name));
            } else {
                MainController.getInstance().setIsCourse(true);
                MainController.getInstance().setCourse(Course.getCourse(selected.name));
            }
        } catch(SQLException e) {
            MainController.getInstance().showAlertMessage(e.getMessage());
        }
        MainController.getInstance().changeScene("../view/DetailsScreen.fxml", "Details");
    }

    @FXML
    private void handleLogoutPressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }
}

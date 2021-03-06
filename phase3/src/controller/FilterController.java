package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class FilterController {
    
    @FXML
    private Button meButton, filterButton, resetButton, addButton, removeButton, viewButton, logoutButton;
    
    @FXML
    private TextField titleField;
    
    @FXML
    private ComboBox<String> designationBox, majorBox, yearBox, departmentBox, categoryBox, typeBox;
    
    @FXML
    private TableView<SearchProjectsCourses> table;
    
    @FXML
    private TableColumn nameCol, typeCol;
    
    @FXML
    private ListView<String> categoryList;
    
    private ObservableSet<String> categories = FXCollections.observableSet();
    
    @FXML
    private void initialize() throws SQLException {
        typeBox.getItems().addAll("Project", "Course", "Both");
        for (Category c : Category.selectAllCategory()) {
            categoryBox.getItems().add(c.name);
        }
        for (Designation d : Designation.selectAllDesignation()) {
            designationBox.getItems().add(d.name);
        }
        for (Major m : Major.selectAllMajors()) {
            majorBox.getItems().add(m.name);
        }
        for (Year y : Year.selectAllYears()) {
            yearBox.getItems().add(y.name);
        }
        for (Department d : Department.selectAllDepartments()) {
            departmentBox.getItems().add(d.name);
        }
        
        nameCol.setCellValueFactory(new PropertyValueFactory<SearchProjectsCourses, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<SearchProjectsCourses, String>("isProject"));
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
                    categoryList.getItems(), designationBox.getSelectionModel().getSelectedItem(), departmentBox.getSelectionModel().getSelectedItem(), majorBox.getSelectionModel().getSelectedItem(), yearBox.getSelectionModel().getSelectedItem(), !"Course".equals(typeBox.getSelectionModel().getSelectedItem()), !"Project".equals(typeBox.getSelectionModel().getSelectedItem()));
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
        titleField.clear();
        designationBox.setValue(null);
        majorBox.setValue(null);
        yearBox.setValue(null);
        departmentBox.setValue(null);
        categoryBox.setValue(null);
        typeBox.setValue(null);
        categoryList.getItems().clear();
    }
    
    @FXML
    private void handleViewPressed() {
        // get the type from the selected entry
        // if it is a course, set the isCourse variable in MainController to true
        // also set the course or project variable in MainController
        SearchProjectsCourses selected = table.getSelectionModel().getSelectedItem();
        if(selected != null) {
            try {
                if (selected.isProject) {
                    MainController.getInstance().setIsCourse(false);
                    MainController.getInstance().setProject(Project.getProject(selected.name));
                } else {
                    MainController.getInstance().setIsCourse(true);
                    MainController.getInstance().setCourse(Course.getCourse(selected.name));
                }
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage("Invalid project/course");
            }
            MainController.getInstance().changeScene("../view/DetailsScreen.fxml", "Details");
        } else {
            MainController.getInstance().showAlertMessage("Please select an entry to view");
        }
    }
    
    @FXML
    private void handleLogoutPressed() {
        MainController.getInstance().changeScene("../view/LoginScreen.fxml", "Login");
    }
}

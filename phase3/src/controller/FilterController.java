package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FilterController {

    @FXML
    private Button meButton, filterButton, resetButton, addButton, removeButton, viewButton;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox designationBox, majorBox, yearBox, categoryBox, typeBox;

    @FXML
    private TableView table;

    @FXML
    private TableColumn nameCol, typeCol;

    @FXML
    private ListView categoryList;

    private ObservableSet categories = FXCollections.observableSet();

    @FXML
    private void initialize() {
        typeBox.getItems().addAll("Project", "Course", "Both");
        categoryBox.getItems().addAll("Project", "Course", "Both");
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
}

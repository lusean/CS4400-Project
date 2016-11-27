package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {

    private Stage primaryStage;
    private static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        changeScene("../view/LoginScreen.fxml", "Login");
        mainController = this;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void changeScene(String scenePath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource(scenePath));
            Parent screen = loader.load();

            //sets the scene
            Stage primaryStage = getPrimaryStage();
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(screen));
            primaryStage.show();
        } catch (IOException e) {
            showAlertMessage("Unable to load: " + title);
        }
    }

    public static MainController getInstance() {
        return mainController;
    }

    public void showAlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Alert.AlertType.ERROR.toString());
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void showOKMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
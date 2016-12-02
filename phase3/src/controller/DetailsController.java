package controller;

import entity.Course;
import entity.Project;
import entity.Student;
import entity.StudentProjectApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.applet.Main;

import java.sql.SQLException;

public class DetailsController {

    @FXML
    private TextField titleField, descriptionField;

    @FXML
    private Button backButton, applyButton;

    private boolean isCourse;

    @FXML
    private void initialize() {
        if(MainController.getInstance().isCourse()) {
            applyButton.setVisible(false);
            Course course = MainController.getInstance().getCourse();
            titleField.setText(course.courseNumber);
            // NEED TO DISPLAY COURSE CATEGORIES
            descriptionField.setText(String.format("Course Name: '%s'\n Instructor: '%s'\n Designation: '%s'\n" +
                    "Category: '%s'\n Estimated number of students: '%s'", course.courseName, course.instructor,
                    course.designation, course.estimatedStudent));
        } else {
            Project project = MainController.getInstance().getProject();
            titleField.setText(project.projectName);
        }
    }

    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }

    @FXML
    private void handleApplyPressed() {
        if(MainController.getInstance().isProfileUpdated()) {
            Student student = MainController.getInstance().getStudent();
            Project project = MainController.getInstance().getProject();
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            StudentProjectApplication app = new StudentProjectApplication(student.username, project.projectName, "Pending", sqlDate);
            try {
                app.insert();
            } catch(SQLException e) {
                MainController.getInstance().showAlertMessage(e.getMessage());
            }
            MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
        } else {
            MainController.getInstance().showAlertMessage("Please update your profile to include a year and major");
        }

    }
}


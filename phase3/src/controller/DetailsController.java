package controller;

import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sun.applet.Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailsController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

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
            String str = "";
            try {
                List<CourseCategory> list = CourseCategory.selectCourseCategoriesForCourse(course.courseNumber);
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        str += ", ";
                    }
                    str += list.get(i).category;
                }
            } catch (SQLException e){
                MainController.getInstance().showAlertMessage(e.getMessage());
            }
            descriptionField.setText(String.format("Course Name: %s\nInstructor: %s\nDesignation: %s\n" +
                    "Category: %s\nEstimated number of students: %s", course.courseName, course.instructor,
                    course.designation, str, course.estimatedStudent));
        } else {
            Project project = MainController.getInstance().getProject();
            titleField.setText(project.projectName);
            String str = "";
            try {
                List<ProjectCategory> list = ProjectCategory.selectCourseCategoriesForCourse(project.projectName);
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        str += ", ";
                    }
                    str += list.get(i).category;
                }
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage(e.getMessage());
            }
            String blank = "";
            descriptionField.setText(String.format("Advisor: %s\nDescription: %s\nDesignation: %s\n" +
                    "Category: %s\nRequirements: %s\nEstimated number of students: %s", project.advisorName,
                    project.description, project.designation, str, blank, project.estimatedStudents));
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


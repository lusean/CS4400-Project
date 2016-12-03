package controller;

import entity.Course;
import entity.Project;
import entity.Student;
import entity.StudentProjectApplication;
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
            descriptionField.setText(String.format("Course Name: '%s'\n Instructor: '%s'\n Designation: '%s'\n" +
                    "Category: '%s'\n Estimated number of students: '%s'", course.courseName, course.instructor,
                    course.designation, course.estimatedStudent));
        } else {
            Project project = MainController.getInstance().getProject();
            titleField.setText(project.projectName);
            String reqs = getProjectRestrictions(project);
            descriptionField.setText(String.format("Advisor: '%s'\n Description: '%s'\n Designation: '%s'\n" +
                    "Category: '%s'\n Requirements: '%s'\n Estimated number of students '%s'", project.advisorName,
                    project.description, project.designation, reqs, project.estimatedStudents));
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

    private String getProjectRestrictions(Project p) {
        List<String> list = new ArrayList<>();
        String str = "";
        list.add(p.majorRestriction);
        list.add(p.yearRestriction);
        list.add(p.deptRestriction);
        int index = 0;
        for (String s : list) {
            if (s != null) {
                str += s;
                break;
            }
            index++;
        }
        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(i) != null) {
                str += ", " + list.get(i);
            }
        }
        return str;
    }
}


package controller;

import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
        if (MainController.getInstance().isCourse()) {
            applyButton.setVisible(false);
            Course course = MainController.getInstance().getCourse();
            titleField.setText(course.courseNumber);
            String cat = "";
            try {
                List<CourseCategory> list = CourseCategory.selectCourseCategoriesForCourse(course.courseNumber);
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        cat += ", ";
                    }
                    cat += list.get(i).category;
                }
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage("Invalid course categories");
            }
            descriptionField.setText(String.format("Course Name: %s\nInstructor: %s\nDesignation: %s\n" +
                            "Category: %s\nEstimated number of students: %s", course.courseName, course.instructor,
                    course.designation, cat, course.estimatedStudent));
        } else {
            Project project = MainController.getInstance().getProject();
            titleField.setText(project.projectName);
            String cat = "";
            String req = "";
            try {
                cat = getProjectCategories(project);
                req = getProjectRequirements(project);
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage("Invalid project categories and requirements");
            }
            descriptionField.setText(String.format("Advisor: %s\nDescription: %s\nDesignation: %s\n" +
                            "Category: %s\nRequirements: %s\nEstimated number of students: %s", project.advisorName,
                    project.description, project.designation, cat, req, project.estimatedStudents));
        }
    }
    
    @FXML
    private void handleBackPressed() {
        MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
    }
    
    @FXML
    private void handleApplyPressed() {
        if (MainController.getInstance().isProfileUpdated()) {
            Student student = MainController.getInstance().getStudent();
            Project project = MainController.getInstance().getProject();
            
            try {
                if (!StudentProjectApplication.selectStudentProjectApplicationsForStudentProject(student.username, project.projectName).isEmpty()) {
                    MainController.getInstance().showAlertMessage("Already applied to this project");
                } else {
                    boolean valid = false;
                    
                    if (project.majorRestriction == null && project.deptRestriction == null) {
                        valid = true;
                    } else if (project.majorRestriction != null && project.deptRestriction == null) {
                        valid = project.majorRestriction.equals(student.major);
                    } else if (project.majorRestriction == null && project.deptRestriction != null) {
                        valid = project.deptRestriction.equals(Major.selectMajor(student.major).get(0).department);
                    } else if (project.majorRestriction != null && project.deptRestriction != null) {
                        valid = project.deptRestriction.equals(Major.selectMajor(student.major).get(0).department) && project.majorRestriction.equals(student.major);
                    }
                    if (project.yearRestriction != null && !project.yearRestriction.equals(student.year)) {
                        valid = false;
                    }
                    
                    if (valid) {
                        java.util.Date date = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        StudentProjectApplication app = new StudentProjectApplication(student.username, project.projectName, "Pending", sqlDate);
                        app.insert();
                    } else {
                        MainController.getInstance().showAlertMessage("Cannot apply to this project");
                    }
                }
            } catch (SQLException e) {
                MainController.getInstance().showAlertMessage("Invalid application");
            }
            MainController.getInstance().changeScene("../view/FilterScreen.fxml", "Main Page");
        } else {
            MainController.getInstance().showAlertMessage("Please update your profile to include a year and major");
        }
    }
    
    private String getProjectRequirements(Project p) {
        List<String> list = new ArrayList<>();
        String str = "";
        list.add(p.majorRestriction);
        list.add(p.yearRestriction);
        list.add(p.deptRestriction);
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                str += ", ";
            }
            if (list.get(i) != null) {
                str += list.get(i);
            } else {
                list.remove(i);
                i--;
            }
        }
        return str;
    }
    
    private String getProjectCategories(Project p) throws SQLException {
        String str = "";
        List<ProjectCategory> list = ProjectCategory.selectProjectCategoriesForProject(p.projectName);
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                str += ", ";
            }
            str += list.get(i).category;
        }
        return str;
    }
}


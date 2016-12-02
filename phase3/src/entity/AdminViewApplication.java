package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminViewApplication extends Entity {
    public String projectName, studentMajor, studentYear, applyStatus, studentName;
    
    public AdminViewApplication(String projectName, String studentMajor, String studentYear, String applyStatus, String studentName) {
        this.projectName = projectName;
        this.studentMajor = studentMajor;
        this.studentYear = studentYear;
        this.applyStatus = applyStatus;
        this.studentName = studentName;
    }
    
    public AdminViewApplication(ResultSet rs) throws SQLException {
        this.projectName = rs.getString(1);
        this.studentMajor = rs.getString(2);
        this.studentYear = rs.getString(3);
        this.applyStatus = rs.getString(4);
        this.studentName = rs.getString(5);
    }
    
    public static List<AdminViewApplication> selectAllAdminViewApplications() throws SQLException {
        return Entity.select("SELECT SPA.Project, S.Major, S.Year, SPA.ApplyStatus, S.Username FROM StudentProjectApplications SPA, Students S WHERE SPA.Student = S.Username;", AdminViewApplication::new);
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void updateApplyStatus(String status) throws SQLException {
        applyStatus = status;
        //sql update stuff
    }
}

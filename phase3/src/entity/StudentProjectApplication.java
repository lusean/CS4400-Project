package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentProjectApplication extends Entity {
    public String student, project, applyStatus;
    public Date applyDate;
    
    public StudentProjectApplication(String student, String project, String applyStatus, Date applyDate) {
        this.student = student;
        this.project = project;
        this.applyStatus = applyStatus;
        this.applyDate = applyDate;
    }
    
    public StudentProjectApplication(ResultSet rs) throws SQLException {
        this.student = rs.getString(1);
        this.project = rs.getString(2);
        this.applyStatus = rs.getString(3);
        this.applyDate = rs.getDate(4);
    }
    
    public static List<StudentProjectApplication> selectAllStudentProjectApplications() throws SQLException {
        return Entity.select("SELECT * FROM StudentProjectApplications;", StudentProjectApplication::new);
    }
    
    public static List<StudentProjectApplication> selectStudentProjectApplicationsForStudent(String username) throws SQLException {
        return Entity.select(String.format("SELECT * FROM StudentProjectApplications WHERE Student = '%s';", username), StudentProjectApplication::new);
    }

    public static int getTotalApplications() throws SQLException {
        List<StudentProjectApplication> list = Entity.select("SELECT * FROM StudentProjectApplications;", StudentProjectApplication::new);
        return list.size();
    }

    public static int getAcceptedApplications() throws SQLException {
        List<StudentProjectApplication> list = Entity.select("SELECT * FROM StudentProjectApplications WHERE ApplyStatus = 'Accepted';", StudentProjectApplication::new);
        return list.size();
    }

    public void insert() throws SQLException {
        execute(String.format("INSERT INTO StudentProjectApplications VALUES ('%s', '%s', '%s', '%s');", student, project, applyStatus, applyDate));
    }
    
    public void updateStatus(String newStatus) throws SQLException {
        applyStatus = newStatus;
        execute(String.format("UPDATE StudentProjectApplications SET ApplyStatus = '%s' WHERE Student = '%s' AND Project = '%s';", applyStatus, student, project));
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public String getProject() {
        return project;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public String getStudent() {
        return student;
    }
}

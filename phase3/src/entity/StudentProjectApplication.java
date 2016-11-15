package entity;

import java.sql.Connection;
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
    
    public static List<StudentProjectApplication> selectAllStudentProjectApplications(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM StudentProjectApplications;", StudentProjectApplication::new);
    }
    
    public static List<StudentProjectApplication> selectStudentProjectApplicationsForStudent(Connection conn, String username) throws SQLException {
        return Entity.select(conn, String.format("SELECT * FROM StudentProjectApplications WHERE Student = '%s';", username), StudentProjectApplication::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO StudentProjectApplications VALUES ('%s', '%s', '%s', '%s');", student, project, applyStatus, applyDate));
    }
    
    public void updateStatus(Connection conn, String newStatus) throws SQLException {
        applyStatus = newStatus;
        execute(conn, String.format("UPDATE StudentProjectApplications SET ApplyStatus = '%s' WHERE Student = '%s' AND Project = '%s';", applyStatus, student, project));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM StudentProjectApplications;");
    }
}

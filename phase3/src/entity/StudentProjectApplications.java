package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentProjectApplications extends Entity {
    public String student, project, applyStatus;
    
    public StudentProjectApplications(String student, String project, String applyStatus) {
        this.student = student;
        this.project = project;
        this.applyStatus = applyStatus;
    }
    
    public StudentProjectApplications(ResultSet rs) throws SQLException {
        this.student = rs.getString(1);
        this.project = rs.getString(2);
        this.applyStatus = rs.getString(3);
    }
    
    public static List<StudentProjectApplications> selectAllStudentProjectApplications(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM StudentProjectApplications;", StudentProjectApplications::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO StudentProjectApplications VALUES ('%s', '%s', '%s');", student, project, applyStatus));
    }
    
    public void updateStatus(Connection conn, String newStatus) throws SQLException {
        applyStatus = newStatus;
        execute(conn, String.format("UPDATE StudentProjectApplications SET ApplyStatus = '%s' WHERE Student = '%s' AND Project = '%s';", applyStatus, student, project));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM StudentProjectApplications;");
    }
}

package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentProjectApplications extends InsertableEntity {
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
       return selectStudentProjectApplicationsWhere(conn, new WhereClause[]{});
    }
    
    public static List<StudentProjectApplications> selectStudentProjectApplicationsWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<StudentProjectApplications>) Entity.select(conn, "StudentProjectApplications", StudentProjectApplications::new, wheres);
    }
    
    @Override
    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, student);
        ps.setString(2, project);
        ps.setString(3, applyStatus);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 3, "StudentProjectApplications");
    }
    
    public void updateStatus(Connection conn, String newStatus) throws SQLException {
        applyStatus = newStatus;
        PreparedStatement ps = conn.prepareStatement("UPDATE StudentProjectApplications SET ApplyStatus = ? WHERE Student = ? AND Project = ?");
        ps.setString(1, newStatus);
        ps.setString(2, student);
        ps.setString(3, project);
        ps.executeUpdate();
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "StudentProjectApplications");
    }
}

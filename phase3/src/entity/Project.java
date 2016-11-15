package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Project extends Entity {
    public String projectName, advisorName, advisorEmail, description, designation, majorRestriction, yearRestriction, deptRestriction;
    public int estimatedStudents;
    
    public Project(String projectName, String advisorName, String advisorEmail, int estimatedStudents, String description, String designation, String majorRestriction, String yearRestriction, String deptRestriction) {
        this.projectName = projectName;
        this.advisorName = advisorName;
        this.advisorEmail = advisorEmail;
        this.description = description;
        this.designation = designation;
        this.majorRestriction = majorRestriction;
        this.yearRestriction = yearRestriction;
        this.deptRestriction = deptRestriction;
        this.estimatedStudents = estimatedStudents;
    }
    
    public Project(ResultSet rs) throws SQLException {
        this.projectName = rs.getString(1);
        this.advisorName = rs.getString(2);
        this.advisorEmail = rs.getString(3);
        this.estimatedStudents = rs.getInt(4);
        this.description = rs.getString(5);
        this.designation = rs.getString(6);
        this.majorRestriction = rs.getString(7);
        this.yearRestriction = rs.getString(8);
        this.deptRestriction = rs.getString(9);
    }
    
    public static List<Project> selectAllProjects(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM Projects;", Project::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO Projects VALUES ('%s', '%s', '%s', %d, '%s', '%s', %s, %s, %s);", projectName, advisorName, advisorEmail, estimatedStudents, description, designation, majorRestriction == null ? "NULL" : "'" + majorRestriction + "'", yearRestriction == null ? "NULL" : "'" + yearRestriction + "'", deptRestriction == null ? "NULL" : "'" + deptRestriction + "'"));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM Projects;");
    }
}

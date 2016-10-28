package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Project extends InsertableEntity {
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
       return selecProjectsWhere(conn, new WhereClause[]{});
    }
    
    public static List<Project> selecProjectsWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Project>) Entity.select(conn, "Project", Project::new, wheres);
    }
    
    @Override
    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, projectName);
        ps.setString(2,advisorName );
        ps.setString(3,advisorEmail );
        ps.setInt(4, estimatedStudents);
        ps.setString(5, description);
        ps.setString(6, designation);
        ps.setString(7, majorRestriction);
        ps.setString(8, yearRestriction);
        ps.setString(9, deptRestriction);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 9, "Project");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "Project");
    }
}

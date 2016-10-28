package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Course extends InsertableEntity {
    public String courseNumber, courseName, instructor, designation;
    public int estimatedStudent;
    
    public Course(String courseNumber, String courseName, String instructor, int estimatedStudent, String designation) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.instructor = instructor;
        this.designation = designation;
        this.estimatedStudent = estimatedStudent;
    }
    
    public Course(ResultSet rs) throws SQLException {
        this.courseNumber = rs.getString(1);
        this.courseName = rs.getString(2);
        this.instructor = rs.getString(3);
        this.estimatedStudent = rs.getInt(4);
        this.designation = rs.getString(5);
    }
    
    public static List<Course> selectAllCourses(Connection conn) throws SQLException {
       return selectCoursesWhere(conn, new WhereClause[]{});
    }
    
    public static List<Course> selectCoursesWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Course>) Entity.select(conn, "Course", Course::new, wheres);
    }
    
    @Override
    protected void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, courseNumber);
        ps.setString(2, courseName);
        ps.setString(3, instructor);
        ps.setInt(4, estimatedStudent);
        ps.setString(5, designation);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 5, "Course");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "Course");
    }
}

package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Course extends Entity {
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
        return Entity.select(conn, "SELECT * FROM Course;", Course::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO Course VALUES ('%s', '%s', '%s', %d, '%s');", courseNumber, courseName, instructor, estimatedStudent, designation));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM Course;");
    }
}

package entity;

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
    
    public static List<Course> selectAllCourses() throws SQLException {
        return Entity.select("SELECT * FROM Courses;", Course::new);
    }
    
    public void insert() throws SQLException {
        execute(String.format("INSERT INTO Courses VALUES ('%s', '%s', '%s', %d, '%s');", courseNumber, courseName, instructor, estimatedStudent, designation));
    }
    
    public static void deleteAll() throws SQLException {
        execute("DELETE FROM Courses;");
    }
}

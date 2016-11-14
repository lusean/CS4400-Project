package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Student extends Entity {
    public String username, email, year, major;
    
    public Student(String username, String email, String year, String major) {
        this.username = username;
        this.email = email;
        this.year = year;
        this.major = major;
    }
    
    public Student(ResultSet rs) throws SQLException {
        this.username = rs.getString(1);
        this.email = rs.getString(2);
        this.year = rs.getString(3);
        this.major = rs.getString(4);
    }
    
    public void updateYear(Connection conn, String newYear) throws SQLException {
        year = newYear;
        execute(conn, String.format("UPDATE StudentProjectApplications SET Year = '%s' WHERE Username = '%s';", year, username));
    }
    
    public void updateMajor(Connection conn, String newMajor) throws SQLException {
        major = newMajor;
        execute(conn, String.format("UPDATE StudentProjectApplications SET Major = '%s' WHERE Username = '%s';", year, major));
    }
    
    public static List<Student> selectAllStudents(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM Student;", Student::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO Student VALUES ('%s', '%s', %s, %s);", username, email, year == null ? "NULL" : "'" + year + "'", major == null ? "NULL" : "'" + major + "'"));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM Student;");
    }
}

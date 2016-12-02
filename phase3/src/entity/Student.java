package entity;

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
    
    public void updateYear(String newYear) throws SQLException {
        execute(String.format("UPDATE Students SET Year = '%s' WHERE Username = '%s';", newYear, username));
        year = newYear;
    }
    
    public void updateMajor(String newMajor) throws SQLException {
        execute(String.format("UPDATE Students SET Major = '%s' WHERE Username = '%s';", newMajor, username));
        major = newMajor;
    }
    
    public static List<Student> selectAllStudents() throws SQLException {
        return Entity.select("SELECT * FROM Students;", Student::new);
    }
    
    public void insert() throws SQLException {
        execute(String.format("INSERT INTO Students VALUES ('%s', '%s', %s, %s);", username, email, year == null ? "NULL" : "'" + year + "'", major == null ? "NULL" : "'" + major + "'"));
    }

    public String getUsername() {
        return username;
    }
}

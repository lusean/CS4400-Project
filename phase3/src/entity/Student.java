package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Student extends InsertableEntity {
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
    
    public static List<Student> selectAllStudents(Connection conn) throws SQLException {
      return  selectStudentsWhere(conn, new WhereClause[]{});
    }
    
    public static List<Student> selectStudentsWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Student>) Entity.select(conn, "Student", Student::new, wheres);
    }
    
    @Override
    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, year);
        ps.setString(4, major);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 4, "Student");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "Student");
    }
}

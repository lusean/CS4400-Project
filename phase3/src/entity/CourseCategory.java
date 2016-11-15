package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseCategory extends Entity {
    public String course, category;
    
    public CourseCategory(String course, String category) {
        this.course = course;
        this.category = category;
    }
    
    public CourseCategory(ResultSet rs) throws SQLException {
        this.course = rs.getString(1);
        this.category = rs.getString(2);
    }
    
    public static List<CourseCategory> selectAllCourseCategories(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM CourseCategories;", CourseCategory::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO CourseCategories VALUES ('%s', '%s');", course, category));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM CourseCategories;");
    }
}

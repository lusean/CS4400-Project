package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseCategories extends Entity {
    public String course, category;
    
    public CourseCategories(String course, String category) {
        this.course = course;
        this.category = category;
    }
    
    public CourseCategories(ResultSet rs) throws SQLException {
        this.course = rs.getString(1);
        this.category = rs.getString(2);
    }
    
    public static List<CourseCategories> selectAllCourseCategories(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM CourseCategories;", CourseCategories::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO CourseCategories VALUES ('%s', '%s');", course, category));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM CourseCategories;");
    }
}

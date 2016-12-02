package entity;

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
    
    public static List<CourseCategory> selectAllCourseCategories() throws SQLException {
        return Entity.select("SELECT * FROM CourseCategories;", CourseCategory::new);
    }
    
    public void insert() throws SQLException {
        execute(String.format("INSERT INTO CourseCategories VALUES ('%s', '%s');", course, category));
    }
}

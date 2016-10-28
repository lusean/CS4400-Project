package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseCategories extends InsertableEntity {
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
        return selectCourseCategoriesWhere(conn, new WhereClause[]{});
    }
    
    public static List<CourseCategories> selectCourseCategoriesWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<CourseCategories>) Entity.select(conn, "CourseCategories", CourseCategories::new, wheres);
    }
    
    @Override
    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, course);
        ps.setString(2, category);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 2, "CourseCategories");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "CourseCategories");
    }
}

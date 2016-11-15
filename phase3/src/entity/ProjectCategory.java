package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectCategory extends Entity {
    public String project, category;
    
    public ProjectCategory(String project, String category) {
        this.project = project;
        this.category = category;
    }
    
    public ProjectCategory(ResultSet rs) throws SQLException {
        this.project = rs.getString(1);
        this.category = rs.getString(2);
    }
    
    public static List<ProjectCategory> selectAllProjectCategories(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM ProjectCategories;", ProjectCategory::new);
    }
    
    public void insert(Connection conn) throws SQLException {
        execute(conn, String.format("INSERT INTO ProjectCategories VALUES ('%s', '%s');", project, category));
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        execute(conn, "DELETE FROM ProjectCategories;");
    }
}

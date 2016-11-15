package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Category extends Entity {
    public String name;
    
    public Category(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<Category> selectAllUsers() throws SQLException {
        return Entity.select("SELECT * FROM Categories;", Category::new);
    }
}

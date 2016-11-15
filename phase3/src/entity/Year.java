package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Year extends Entity {
    public String name;
    
    public Year(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<Year> selectAllYears() throws SQLException {
        return Entity.select("SELECT * FROM Years;", Year::new);
    }
}

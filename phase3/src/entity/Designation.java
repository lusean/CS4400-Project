package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Designation extends Entity {
    public String name;
    
    public Designation(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<Designation> selectAllDesignation() throws SQLException {
        return Entity.select("SELECT * FROM Designations;", Designation::new);
    }
}

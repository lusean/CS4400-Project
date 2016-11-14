package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Department extends Entity {
    public String name;
    
    public Department(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<Department> selectAllDepartments(Connection conn) throws SQLException {
        return Entity.select(conn, "SELECT * FROM Department;", Department::new);
    }
}

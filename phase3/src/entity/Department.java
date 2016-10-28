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
       return selectDepartmentsWhere(conn, new WhereClause[]{});
    }
    
    public static List<Department> selectDepartmentsWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Department>) Entity.select(conn, "Department", Department::new, wheres);
    }
}

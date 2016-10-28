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
    
    public static List<Designation> selectAllDesignation(Connection conn) throws SQLException {
       return selectDesignationWhere(conn, new WhereClause[]{});
    }
    
    public static List<Designation> selectDesignationWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Designation>) Entity.select(conn, "Designation", Designation::new, wheres);
    }
}

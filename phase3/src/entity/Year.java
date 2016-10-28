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
    
    public static List<Year> selectAllYears(Connection conn) throws SQLException {
       return selectYearsWhere(conn, new WhereClause[]{});
    }
    
    public static List<Year> selectYearsWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<Year>) Entity.select(conn, "Year", Year::new, wheres);
    }
}

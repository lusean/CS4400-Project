package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplyStatus extends Entity {
    public String name;
    
    public ApplyStatus(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<ApplyStatus> selectAllUsers(Connection conn) throws SQLException {
       return selectAllUsers(conn, new WhereClause[]{});
    }
    
    public static List<ApplyStatus> selectAllUsers(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<ApplyStatus>) Entity.select(conn, "ApplyStatus", ApplyStatus::new, wheres);
    }
}

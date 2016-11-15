package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplyStatus extends Entity {
    public String name;
    
    public ApplyStatus(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
    }
    
    public static List<ApplyStatus> selectAllUsers() throws SQLException {
        return Entity.select("SELECT * FROM ApplyStatuses;", ApplyStatus::new);
    }
}

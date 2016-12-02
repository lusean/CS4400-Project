package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Major extends Entity {
    public String name, department;
    
    public Major(ResultSet rs) throws SQLException {
        this.name = rs.getString(1);
        this.department = rs.getString(2);
    }
    
    public static List<Major> selectAllMajors() throws SQLException {
        return Entity.select("SELECT * FROM Majors;", Major::new);
    }
    
    public static List<Major> selectMajor(String majorName) throws SQLException {
        return Entity.select(String.format("SELECT * FROM Majors WHERE MajorName = '%s';", majorName), Major::new);
    }
}

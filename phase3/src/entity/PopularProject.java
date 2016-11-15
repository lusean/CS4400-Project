package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PopularProject extends Entity {
    public String project;
    public int numApplicants;
    
    public PopularProject(String project, int numApplicants) {
        this.project = project;
        this.numApplicants = numApplicants;
    }
    
    public PopularProject(ResultSet rs) throws SQLException {
        this.project = rs.getString(1);
        this.numApplicants = rs.getInt(2);
    }
    
    public static List<PopularProject> selectPopularProjects() throws SQLException {
        return Entity.select("SELECT Project, COUNT(*) AS Cnt FROM StudentProjectApplications GROUP BY Project ORDER BY Cnt DESC LIMIT 10;", PopularProject::new);
    }
}

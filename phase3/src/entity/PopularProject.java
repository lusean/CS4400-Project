package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by aeubanks on 15/11/16.
 */
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
        List<PopularProject> ret = Entity.select("SELECT Project, COUNT(*) AS Cnt FROM StudentProjectApplications GROUP BY Project ORDER BY Cnt DESC LIMIT 10;", PopularProject::new);
        // only return top 10
        while (ret.size() > 10) {
            ret.remove(ret.size() - 1);
        }
        return ret;
    }
}

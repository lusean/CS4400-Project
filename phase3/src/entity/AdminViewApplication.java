package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by aeubanks on 15/11/16.
 */
public class AdminViewApplication extends Entity {
    public String projectName, studentMajor, studentYear, applyStatus;
    
    public AdminViewApplication(String projectName, String studentMajor, String studentYear, String applyStatus) {
        this.projectName = projectName;
        this.studentMajor = studentMajor;
        this.studentYear = studentYear;
        this.applyStatus = applyStatus;
    }
    
    public AdminViewApplication(ResultSet rs) throws SQLException {
        this.projectName = rs.getString(1);
        this.studentMajor =rs.getString(2);
        this.studentYear = rs.getString(3);
        this.applyStatus = rs.getString(4);
    }
    
    public static List<AdminViewApplication> selectAllAdminViewApplications() throws SQLException {
        return Entity.select("SELECT SPA.Project, S.Major, S.Year, SPA.ApplyStatus FROM StudentProjectApplications SPA, Students S WHERE SPA.Student = S.Username;", AdminViewApplication::new);
    }
}

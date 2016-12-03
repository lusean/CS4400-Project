package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplicationReport extends Entity {
    public String projectName;
    public int numApplicants;
    public double acceptRate;
    public String topMajor1, topMajor2, topMajor3;

    public ApplicationReport(String projectName, int numApplicants, double acceptRate, String topMajor1, String topMajor2, String topMajor3) {
        this.projectName = projectName;
        this.numApplicants = numApplicants;
        this.acceptRate = acceptRate;
        this.topMajor1 = topMajor1;
        this.topMajor2 = topMajor2;
        this.topMajor3 = topMajor3;
    }

    public ApplicationReport(ResultSet rs) throws SQLException {
        this.projectName = rs.getString(1);
        this.numApplicants = rs.getInt(2);
        this.acceptRate = rs.getDouble(3);
        this.topMajor1 = rs.getString(4);
        this.topMajor2 = rs.getString(5);
        this.topMajor3 = rs.getString(6);
    }

    public static List<ApplicationReport> getApplicationReports() throws SQLException {
        String sql = "SELECT A.Project, A.ApplicantCount, B.AcceptedCount / A.ApplicantCount as AcceptRate, " +
                "(SELECT (CASE WHEN (SELECT COUNT(DISTINCT Students.Major) FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project) >= 1 THEN (select Students.Major FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project GROUP BY Students.Major ORDER BY COUNT(*), Students.Major LIMIT 1) ELSE NULL END)) TopMajor1, " +
                "(SELECT (CASE WHEN (SELECT COUNT(DISTINCT Students.Major) FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project) >= 2 THEN (select Students.Major FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project GROUP BY Students.Major ORDER BY COUNT(*), Students.Major LIMIT 1 OFFSET 1) ELSE NULL END)) TopMajor2, " +
                "(SELECT (CASE WHEN (SELECT COUNT(DISTINCT Students.Major) FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project) >= 3 THEN (select Students.Major FROM StudentProjectApplications InnerSPA, Students WHERE InnerSPA.Student = Students.Username AND A.Project = InnerSPA.Project GROUP BY Students.Major ORDER BY COUNT(*), Students.Major LIMIT 1 OFFSET 2) ELSE NULL END)) TopMajor3 " +
                "FROM (SELECT Project, COUNT(*) as ApplicantCount FROM StudentProjectApplications GROUP BY Project) A LEFT JOIN (SELECT Project, COUNT(*) as AcceptedCount FROM StudentProjectApplications WHERE ApplyStatus = 'Accepted' GROUP BY Project) B ON A.Project = B.Project;";

        return Entity.select(sql, ApplicationReport::new);
    }

    public String getProjectName() {
        return projectName;
    }

    public int getNumApplicants() {
        return numApplicants;
    }

    public double getAcceptRate() {
        return acceptRate;
    }

    public String getTopMajors() {
        String str = "";
        String[] array = {topMajor1, topMajor2, topMajor3};
        for (String s : array) {
            boolean firstEntry = true;
            if (!firstEntry && s != null) {
                str += ", ";
            }
            if (s != null) {
                str += s;
                firstEntry = false;
            }
        }
        return str;
    }
}
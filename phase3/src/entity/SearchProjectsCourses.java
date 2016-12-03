package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchProjectsCourses extends Entity {
    public boolean isProject; // true = project, false = course
    public String name;
    
    public SearchProjectsCourses(boolean isProject, String name) {
        this.isProject = isProject;
        this.name = name;
    }
    
    public SearchProjectsCourses(ResultSet rs) throws SQLException {
        this.isProject = rs.getBoolean(1);
        this.name = rs.getString(2);
    }

    public String getName() {
        return name;
    }

    public String getIsProject() {
        if(isProject) {
            return "Project";
        } else {
            return "Course";
        }
    }

    public static List<SearchProjectsCourses> selectAllProjectsAndCourses(String titleFilter, List<String> categoryFilters, String designationFilter, String majorFilter, String yearFilter) throws SQLException {
        if (titleFilter.isEmpty()) {
            titleFilter = null;
        }
        if (titleFilter != null) {
            titleFilter = "*" + titleFilter + "*";
        }
        String titleStr = stringOrNull(titleFilter);
        String categoryStr = "''";
//        String categoryStr = "(NULL";
//        for (String categoryFilter : categoryFilters) {
//           categoryStr += ", '" + categoryFilter + "'";
//        }
//        categoryStr += ")";
        String designationStr = stringOrNull(designationFilter);
        String majorStr = stringOrNull(majorFilter);
        String yearStr = stringOrNull(yearFilter);
        
        String sql = String.format(
                "SELECT true as IsProject, Projects.ProjectName FROM Projects WHERE (%s IS NULL OR Projects.ProjectName LIKE %s) AND (%s IS NULL OR (Projects.ProjectName, %s) IN (SELECT * FROM ProjectCategories)) AND (%s IS NULL OR Projects.Designation IS NULL OR %s = Projects.Designation) AND (%s IS NULL OR Projects.YearRestriction IS NULL OR %s = Projects.YearRestriction) AND ((%s IS NULL OR Projects.MajorRestriction IS NULL OR %s = Projects.MajorRestriction) OR (%s IS NULL OR Projects.DepartmentRestriction IS NULL OR %s = Projects.DepartmentRestriction)) \n" +
                        "UNION ALL \n" +
                        "SELECT false as IsProject, Courses.CourseName FROM Courses WHERE (%s IS NULL OR Courses.CourseName LIKE %s) AND (%s IS NULL OR (Courses.CourseNumber, %s) IN (SELECT * FROM CourseCategories)) AND (%s IS NULL OR Courses.Designation IS NULL OR %s = Courses.Designation);",
                
                titleStr, titleStr,
                categoryStr, categoryStr,
                designationStr, designationStr,
                yearStr, yearStr,
                majorStr, majorStr,
                
                titleStr, titleStr,
                categoryStr, categoryStr,
                designationStr, designationStr);
        return Entity.select(sql, SearchProjectsCourses::new);
    }
}

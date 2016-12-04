package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchProjectsCourses extends Entity {
    public boolean isProject; // true = project, false = course
    public String name;
    public String type;
    
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

    public static List<SearchProjectsCourses> selectAllProjectsAndCourses(String titleFilter, List<String> categoryFilters, String designationFilter, String departmentFilter, String majorFilter, String yearFilter, boolean acceptProjects, boolean acceptCourses) throws SQLException {
        if (departmentFilter != null || majorFilter != null || yearFilter != null) {
            acceptCourses = false;
        }
        if (titleFilter.isEmpty()) {
            titleFilter = null;
        }
        if (titleFilter != null) {
            titleFilter = "%" + titleFilter + "%";
        }
        String titleStr = stringOrNull(titleFilter);
        String designationStr = stringOrNull(designationFilter);
        String majorStr = stringOrNull(majorFilter);
        String yearStr = stringOrNull(yearFilter);
        String departmentStr = stringOrNull(departmentFilter);
        
        String dropRetSql = "DROP TABLE IF EXISTS ret;";
        Entity.execute(dropRetSql);
        String createRetSql = "CREATE TABLE IF NOT EXISTS ret (IsProject INT, Name varchar(100));";
        Entity.execute(createRetSql);
        
        String dropTmpSql = "DROP TABLE IF EXISTS tmp;";
        Entity.execute(dropTmpSql);
        String createTmpSql = "CREATE TABLE IF NOT EXISTS tmp (Category varchar(100));";
        Entity.execute(createTmpSql);
        
        {
            String insertTmpSql = "INSERT INTO tmp VALUES (NULL)";
            for (String categoryFilter : categoryFilters) {
                insertTmpSql += ", ('" + categoryFilter + "')";
            }
            insertTmpSql += ";";
            
            Entity.execute(insertTmpSql);
        }
        
        if (acceptCourses) {
            String sql = String.format(
                            "INSERT INTO ret SELECT false as IsProject, Courses.CourseName FROM Courses WHERE (%s IS NULL OR Courses.CourseName LIKE %s) AND (0 = (SELECT COUNT(Category) FROM tmp) OR EXISTS (SELECT * FROM tmp, CourseCategories WHERE tmp.Category = CourseCategories.Category AND CourseCategories.Course = Courses.CourseNumber)) AND (%s IS NULL OR Courses.Designation IS NULL OR %s = Courses.Designation);",
            
                    titleStr, titleStr,
                    designationStr, designationStr);
            Entity.execute(sql);
        }
        if (acceptProjects) {
            String sql = String.format(
                    "INSERT INTO ret SELECT true as IsProject, Projects.ProjectName FROM Projects WHERE (%s IS NULL OR Projects.ProjectName LIKE %s) AND (0 = (SELECT COUNT(Category) FROM tmp) OR EXISTS (SELECT * FROM tmp, ProjectCategories WHERE tmp.Category = ProjectCategories.Category AND ProjectCategories.Project = Projects.ProjectName)) AND (%s IS NULL OR Projects.Designation IS NULL OR %s = Projects.Designation) AND (%s IS NULL OR Projects.YearRestriction IS NULL OR %s = Projects.YearRestriction) AND (%s IS NULL OR Projects.MajorRestriction IS NULL OR %s = Projects.MajorRestriction) AND (%s IS NULL OR Projects.DepartmentRestriction IS NULL OR %s = Projects.DepartmentRestriction);",
            
                    titleStr, titleStr,
                    designationStr, designationStr,
                    yearStr, yearStr,
                    majorStr, majorStr,
                    departmentStr, departmentStr);
            
            Entity.execute(sql);
        }
        
        return Entity.select("SELECT * FROM ret", SearchProjectsCourses::new);
    }
}

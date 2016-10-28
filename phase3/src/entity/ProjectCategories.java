package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectCategories extends InsertableEntity {
    public String project, category;
    
    public ProjectCategories(String project, String category) {
        this.project = project;
        this.category = category;
    }
    
    public ProjectCategories(ResultSet rs) throws SQLException {
        this.project = rs.getString(1);
        this.category = rs.getString(2);
    }
    
    public static List<ProjectCategories> selectAllProjectCategories(Connection conn) throws SQLException {
        return selectProjectCategoriesWhere(conn, new WhereClause[]{});
    }
    
    public static List<ProjectCategories> selectProjectCategoriesWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<ProjectCategories>) Entity.select(conn, "ProjectCategories", ProjectCategories::new, wheres);
    }
    
    @Override
    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, project);
        ps.setString(2, category);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 2, "ProjectCategories");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "ProjectCategories");
    }
}

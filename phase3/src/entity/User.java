package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User extends InsertableEntity {
    public String username, password;
    public boolean isAdmin;
    
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public User(ResultSet rs) throws SQLException {
        this.username = rs.getString(1);
        this.password = rs.getString(2);
        this.isAdmin = rs.getBoolean(3);
    }
    
    public static List<User> selectAllUsers(Connection conn) throws SQLException {
        return selectUsersWhere(conn, new WhereClause[] {});
    }
    
    public static List<User> selectUsersWhere(Connection conn, WhereClause[] wheres) throws SQLException {
        return (List<User>) Entity.select(conn, "User", User::new, wheres);
    }
    
    @Override
    protected void setPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setBoolean(3, isAdmin);
    }
    
    @Override
    public void insert(Connection conn) throws SQLException {
        insert(conn, 3, "User");
    }
    
    public static void deleteAll(Connection conn) throws SQLException {
        deleteAll(conn, "User");
    }
}

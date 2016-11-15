package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User extends Entity {
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
    
    public static List<User> selectAllUsers() throws SQLException {
        return Entity.select("SELECT * FROM Users;", User::new);
    }
    
    public void insert() throws SQLException {
        execute(String.format("INSERT INTO Users VALUES ('%s', '%s', %d);", username, password, isAdmin ? 1 : 0));
    }
    
    public static void deleteAll() throws SQLException {
        execute("DELETE FROM Users;");
    }
}

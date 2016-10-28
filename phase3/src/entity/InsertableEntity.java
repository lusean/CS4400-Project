package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class InsertableEntity extends Entity {
    protected abstract void setPreparedStatement(PreparedStatement ps) throws SQLException;
    
    public abstract void insert(Connection conn) throws SQLException;
    
    protected void insert(Connection conn, int numValues, String tableName) throws SQLException {
        String sql = "INSERT INTO " + tableName + " VALUES (%s);";
        String placeholders = null;
        for (int i = 0; i < numValues; i++) {
            if (placeholders == null) {
                placeholders = "?";
            } else {
                placeholders += ", ?";
            }
        }
        sql = String.format(sql, placeholders);
        PreparedStatement ps = conn.prepareStatement(sql);
        setPreparedStatement(ps);
        ps.executeUpdate();
    }
    
    protected static void deleteAll(Connection conn, String tableName) throws SQLException {
        Statement s = conn.createStatement();
        s.execute(String.format("DELETE FROM %s;", tableName));
    }
}

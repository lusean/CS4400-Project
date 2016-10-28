package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected interface CreateEntity {
        Entity createEntity(ResultSet rs) throws SQLException;
    }
    
    public static class WhereClause {
        public String column;
        public String strVal;
        public int intVal;
        public boolean boolVal;
        public int valType;
    
        public WhereClause(String column, String strVal) {
            this.column = column;
            this.strVal = strVal;
            this.valType = 0;
        }
    
        public WhereClause(String column, int intVal) {
            this.column = column;
            this.intVal = intVal;
            this.valType = 1;
        }
    
        public WhereClause(String column, boolean boolVal) {
            this.column = column;
            this.boolVal = boolVal;
            this.valType = 2;
        }
        
        public void setPreparedStatement(PreparedStatement ps, int idx) throws SQLException {
            switch (this.valType) {
                case 0:
                    ps.setString(idx, strVal);
                    break;
                case 1:
                    ps.setInt(idx, intVal);
                    break;
                case 2:
                    ps.setBoolean(idx, boolVal);
                    break;
            }
        }
    }
    
    protected static List select(Connection conn, String tableName, CreateEntity ce, WhereClause[] wheres) throws SQLException {
        List<Entity> ret = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            String whereStr = null;
            for (WhereClause where : wheres) {
                if (whereStr == null) {
                    whereStr = String.format("%s = ?", where.column);
                } else {
                    whereStr += String.format(" AND %s = ?", where.column);
                }
            }
            
            String sql;
            if (whereStr == null) {
                sql = String.format("SELECT * FROM %s;", tableName);
            } else {
                sql = String.format("SELECT * FROM %s WHERE %s;", tableName, whereStr);
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < wheres.length; i++) {
                wheres[i].setPreparedStatement(ps, i + 1);
            }
//            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ret.add(ce.createEntity(rs));
                }
            }
        }
        
        return ret;
    }
}

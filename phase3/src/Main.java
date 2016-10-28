import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String DB_URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_49";
    private static final String USER = "cs4400_Team_49";
    private static final String PASS = "i1kH3Min";
    
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            StudentProjectApplications.deleteAll(conn);
            Project.deleteAll(conn);
            Student.deleteAll(conn);
            User.deleteAll(conn);
            
            new User("admin", "p", true).insert(conn);
            new User("admin2", "p", true).insert(conn);
            new User("admin4", "p", true).insert(conn);
            new User("admin5", "p", true).insert(conn);
            
            new User("user1", "p", false).insert(conn);
            new Student("user1", "e", "Freshman", "Computer Science").insert(conn);
            new User("user2", "p2", false).insert(conn);
            new Student("user2", "e2", "Freshman", "Computer Science").insert(conn);
    
            System.out.println("all users:");
            User.selectAllUsers(conn).forEach(u -> System.out.println(u.username));
            System.out.println();
    
            System.out.println("admins only:");
            User.selectUsersWhere(conn, new Entity.WhereClause[]{new Entity.WhereClause("IsAdmin", true)}).forEach(u -> System.out.println(u.username));
            System.out.println();
            
            new Project("pn", "an", "ae", 2, "d", "Community", null, null, null).insert(conn);
    
            System.out.println("all projects:");
            Project.selectAllProjects(conn).forEach(p -> System.out.println(p.projectName));
            System.out.println();
    
            StudentProjectApplications spa = new StudentProjectApplications("user1", "pn", "Pending");
            spa.insert(conn);
            StudentProjectApplications.selectAllStudentProjectApplications(conn).forEach(
                    s -> {
                        System.out.println(s.student);
                        System.out.println(s.project);
                        System.out.println(s.applyStatus);
                    }
            );
            System.out.println("-->");
            spa.updateStatus(conn, "Accepted");
            StudentProjectApplications.selectAllStudentProjectApplications(conn).forEach(
                    s -> {
                        System.out.println(s.student);
                        System.out.println(s.project);
                        System.out.println(s.applyStatus);
                    }
            );
        }
    }
}

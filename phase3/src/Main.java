import entity.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String DB_URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_41?allowMultiQueries=true";
    private static final String USER = "cs4400_Team_41";
    private static final String PASS = "MNisvl9s";
    
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            executeDrop(conn);
            executeCreate(conn);
//            executeDelete(conn);
            executeInsert(conn);
            
            new User("admin1", "p", true).insert(conn);
            new User("admin2", "p", true).insert(conn);
            new User("admin4", "p", true).insert(conn);
            new User("admin5", "p", true).insert(conn);
            
            new User("user1", "p", false).insert(conn);
            new Student("user1", "e", "Freshman", "Computer Science").insert(conn);
            new User("user2", "p2", false).insert(conn);
            new Student("user2", "e2", "Freshman", "Computer Science").insert(conn);
            
            System.out.println("all users:");
            User.selectAllUsers(conn).forEach(u -> System.out.println(u.username + " " + u.isAdmin));
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
    
    private static void executeDrop(Connection conn) throws SQLException {
        Entity.execute(conn, readFile("drop.sql"));
    }
    
    private static void executeCreate(Connection conn) throws SQLException {
        Entity.execute(conn, readFile("create.sql"));
    }
    
    private static void executeDelete(Connection conn) throws SQLException {
        Entity.execute(conn, readFile("delete.sql"));
    }
    
    private static void executeInsert(Connection conn) throws SQLException {
        Entity.execute(conn, readFile("insert.sql"));
    }
    
    private static String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}

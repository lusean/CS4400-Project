import entity.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Entity.initializeSQL();
        
        executeDrop();
        executeCreate();
//            executeDelete();
        executeInsert();
        
        new User("admin1", "p", true).insert();
        new User("admin2", "p", true).insert();
        new User("admin4", "p", true).insert();
        new User("admin5", "p", true).insert();
        
        new User("user1", "p", false).insert();
        new Student("user1", "e", "Freshman", "Computer Science").insert();
        new User("user2", "p2", false).insert();
        new Student("user2", "e2", "Freshman", "Computer Science").insert();
        
        System.out.println("all users:");
        User.selectAllUsers().forEach(u -> System.out.println(u.username + " " + u.isAdmin));
        System.out.println();
        
        new Project("pn", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn1", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn2", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn3", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn4", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn5", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn6", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn7", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn8", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn9", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn0", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn00", "an", "ae", 2, "d", "Community", null, null, null).insert();
        new Project("pn01", "an", "ae", 2, "d", "Community", null, null, null).insert();
        
        System.out.println("all projects:");
        Project.selectAllProjects().forEach(p -> System.out.println(p.projectName));
        System.out.println();
        
        new StudentProjectApplication("user1", "pn", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn1", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn2", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn3", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn4", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn5", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn6", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn7", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn8", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn9", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn0", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn00", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user1", "pn01", "Pending", new Date(10909009)).insert();
        new StudentProjectApplication("user2", "pn01", "Pending", new Date(10909009)).insert();
        
        StudentProjectApplication spa = new StudentProjectApplication("user2", "pn", "Pending", new Date(10909009));
        spa.insert();
        StudentProjectApplication.selectAllStudentProjectApplications().forEach(
                s -> {
                    System.out.println(s.student);
                    System.out.println(s.project);
                    System.out.println(s.applyStatus);
                }
        );
        System.out.println("-->");
        spa.updateStatus("Accepted");
        StudentProjectApplication.selectAllStudentProjectApplications().forEach(
                s -> {
                    System.out.println(s.student);
                    System.out.println(s.project);
                    System.out.println(s.applyStatus);
                }
        );
        
        System.out.println();
        System.out.println("AdminViewApplication:");
        AdminViewApplication.selectAllAdminViewApplications().forEach(a -> System.out.printf("%s - %s - %s - %s\n", a.projectName, a.studentMajor, a.studentYear, a.applyStatus));
        
        System.out.println();
        System.out.println("Popular Projects:");
        PopularProject.selectPopularProjects().forEach(p -> System.out.println(p.project + " " + p.numApplicants));
        
        Entity.endSQL();
    }
    
    private static void executeDrop() throws SQLException {
        Entity.execute(readFile("drop.sql"));
    }
    
    private static void executeCreate() throws SQLException {
        Entity.execute(readFile("create.sql"));
    }
    
    private static void executeDelete() throws SQLException {
        Entity.execute(readFile("delete.sql"));
    }
    
    private static void executeInsert() throws SQLException {
        Entity.execute(readFile("insert.sql"));
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

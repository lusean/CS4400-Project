CREATE TABLE Year
 (YearName VARCHAR(50) NOT NULL,
 PRIMARY KEY (YearName) ) ENGINE=InnoDB;

CREATE TABLE Department
 (DepartmentName VARCHAR(50) NOT NULL,
 PRIMARY KEY (DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE Major
 (MajorName VARCHAR(50) NOT NULL,
 Department VARCHAR(50) NOT NULL,
 PRIMARY KEY (MajorName),
 FOREIGN KEY (Department) REFERENCES Department(DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE User
 (Username VARCHAR(50) NOT NULL,
 Password VARCHAR(50) NOT NULL,
 IsAdmin BOOLEAN NOT NULL,
 PRIMARY KEY (Username) ) ENGINE=InnoDB;

CREATE TABLE Student
 (Username VARCHAR(50) NOT NULL,
 GTEmail VARCHAR(50) NOT NULL,
 Year VARCHAR(50),
 Major VARCHAR(50),
 PRIMARY KEY (Username),
 UNIQUE (GTEmail),
 FOREIGN KEY (Username) REFERENCES User(Username),
 FOREIGN KEY (Year) REFERENCES Year(YearName),
 FOREIGN KEY (Major) REFERENCES Major(MajorName) ) ENGINE=InnoDB;

CREATE TABLE Category
 (CategoryName VARCHAR(50) NOT NULL,
 PRIMARY KEY (CategoryName) ) ENGINE=InnoDB;

CREATE TABLE Designation
 (DesignationName VARCHAR(50) NOT NULL,
 PRIMARY KEY (DesignationName) ) ENGINE=InnoDB;

CREATE TABLE ApplyStatus
 (ApplyStatusName VARCHAR(50) NOT NULL,
 PRIMARY KEY (ApplyStatusName) ) ENGINE=InnoDB;

CREATE TABLE Project
 (ProjectName VARCHAR(50) NOT NULL,
 AdvisorName VARCHAR(50) NOT NULL,
 AdvisorEmail VARCHAR(50) NOT NULL,
 EstimatedStudents INT NOT NULL,
 Description VARCHAR(1000) NOT NULL,
 Designation VARCHAR(50) NOT NULL,
 MajorRestriction VARCHAR(50),
 YearRestriction VARCHAR(50),
 DepartmentRestriction VARCHAR(50),
 PRIMARY KEY (ProjectName),
 FOREIGN KEY (Designation) REFERENCES Designation(DesignationName),
 FOREIGN KEY (MajorRestriction) REFERENCES Major(MajorName),
 FOREIGN KEY (YearRestriction) REFERENCES Year(YearName),
 FOREIGN KEY (DepartmentRestriction) REFERENCES Department(DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE StudentProjectApplications
 (Student VARCHAR(50) NOT NULL,
 Project VARCHAR(50) NOT NULL,
 ApplyStatus VARCHAR(50) NOT NULL,
 PRIMARY KEY (Student, Project),
 FOREIGN KEY (Student) REFERENCES Student(Username),
 FOREIGN KEY (Project) REFERENCES Project(ProjectName),
 FOREIGN KEY (ApplyStatus) REFERENCES ApplyStatus(ApplyStatusName) ) ENGINE=InnoDB;

CREATE TABLE ProjectCategories
 (Project VARCHAR(50) NOT NULL,
 Category VARCHAR(50) NOT NULL,
 PRIMARY KEY (Project, Category),
 FOREIGN KEY (Project) REFERENCES Project(ProjectName),
 FOREIGN KEY (Category) REFERENCES Category(CategoryName) ) ENGINE=InnoDB;

CREATE TABLE Course
 (CourseNumber VARCHAR(50) NOT NULL,
 CourseName VARCHAR(50) NOT NULL,
 Instructor VARCHAR(50) NOT NULL, 
 EstimatedStudents INT NOT NULL,
 Designation VARCHAR(50) NOT NULL,
 PRIMARY KEY (CourseNumber),
 UNIQUE (CourseName),
 FOREIGN KEY (Designation) REFERENCES Designation(DesignationName) ) ENGINE=InnoDB;

CREATE TABLE CourseCategories
 (Course VARCHAR(50) NOT NULL,
 Category VARCHAR(50) NOT NULL,
 PRIMARY KEY (Course, Category),
 FOREIGN KEY (Course) REFERENCES Course(CourseNumber), 
 FOREIGN KEY (Category) REFERENCES Category(CategoryName) ) ENGINE=InnoDB;


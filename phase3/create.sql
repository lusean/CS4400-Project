CREATE TABLE Years
 (YearName VARCHAR(100) NOT NULL,
 PRIMARY KEY (YearName) ) ENGINE=InnoDB;

CREATE TABLE Departments
 (DepartmentName VARCHAR(100) NOT NULL,
 PRIMARY KEY (DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE Majors
 (MajorName VARCHAR(100) NOT NULL,
 Department VARCHAR(100) NOT NULL,
 PRIMARY KEY (MajorName),
 FOREIGN KEY (Department) REFERENCES Departments(DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE Users
 (Username VARCHAR(100) NOT NULL,
 Password VARCHAR(100) NOT NULL,
 IsAdmin BOOLEAN NOT NULL,
 PRIMARY KEY (Username) ) ENGINE=InnoDB;

CREATE TABLE Students
 (Username VARCHAR(100) NOT NULL,
 GTEmail VARCHAR(100) NOT NULL,
 Year VARCHAR(100),
 Major VARCHAR(100),
 PRIMARY KEY (Username),
 UNIQUE (GTEmail),
 FOREIGN KEY (Username) REFERENCES Users(Username),
 FOREIGN KEY (Year) REFERENCES Years(YearName),
 FOREIGN KEY (Major) REFERENCES Majors(MajorName) ) ENGINE=InnoDB;

CREATE TABLE Categories
 (CategoryName VARCHAR(100) NOT NULL,
 PRIMARY KEY (CategoryName) ) ENGINE=InnoDB;

CREATE TABLE Designations
 (DesignationName VARCHAR(100) NOT NULL,
 PRIMARY KEY (DesignationName) ) ENGINE=InnoDB;

CREATE TABLE ApplyStatuses
 (ApplyStatusName VARCHAR(100) NOT NULL,
 PRIMARY KEY (ApplyStatusName) ) ENGINE=InnoDB;

CREATE TABLE Projects
 (ProjectName VARCHAR(100) NOT NULL,
 AdvisorName VARCHAR(100) NOT NULL,
 AdvisorEmail VARCHAR(100) NOT NULL,
 EstimatedStudents INT NOT NULL,
 Description VARCHAR(1000) NOT NULL,
 Designation VARCHAR(100) NOT NULL,
 MajorRestriction VARCHAR(100),
 YearRestriction VARCHAR(100),
 DepartmentRestriction VARCHAR(100),
 PRIMARY KEY (ProjectName),
 FOREIGN KEY (Designation) REFERENCES Designations(DesignationName),
 FOREIGN KEY (MajorRestriction) REFERENCES Majors(MajorName),
 FOREIGN KEY (YearRestriction) REFERENCES Years(YearName),
 FOREIGN KEY (DepartmentRestriction) REFERENCES Departments(DepartmentName) ) ENGINE=InnoDB;

CREATE TABLE StudentProjectApplications
 (Student VARCHAR(100) NOT NULL,
 Project VARCHAR(100) NOT NULL,
 ApplyStatus VARCHAR(100) NOT NULL,
 ApplyDate DATE NOT NULL,
 PRIMARY KEY (Student, Project),
 FOREIGN KEY (Student) REFERENCES Students(Username),
 FOREIGN KEY (Project) REFERENCES Projects(ProjectName),
 FOREIGN KEY (ApplyStatus) REFERENCES ApplyStatuses(ApplyStatusName) ) ENGINE=InnoDB;

CREATE TABLE ProjectCategories
 (Project VARCHAR(100) NOT NULL,
 Category VARCHAR(100) NOT NULL,
 PRIMARY KEY (Project, Category),
 FOREIGN KEY (Project) REFERENCES Projects(ProjectName),
 FOREIGN KEY (Category) REFERENCES Categories(CategoryName) ) ENGINE=InnoDB;

CREATE TABLE Courses
 (CourseNumber VARCHAR(100) NOT NULL,
 CourseName VARCHAR(100) NOT NULL,
 Instructor VARCHAR(100) NOT NULL, 
 EstimatedStudents INT NOT NULL,
 Designation VARCHAR(100) NOT NULL,
 PRIMARY KEY (CourseNumber),
 UNIQUE (CourseName),
 FOREIGN KEY (Designation) REFERENCES Designations(DesignationName) ) ENGINE=InnoDB;

CREATE TABLE CourseCategories
 (Course VARCHAR(100) NOT NULL,
 Category VARCHAR(100) NOT NULL,
 PRIMARY KEY (Course, Category),
 FOREIGN KEY (Course) REFERENCES Courses(CourseNumber), 
 FOREIGN KEY (Category) REFERENCES Categories(CategoryName) ) ENGINE=InnoDB;


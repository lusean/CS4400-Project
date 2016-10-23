CREATE TABLE Year
 (YearID INT NOT NULL,
 YearName VARCHAR(50) NOT NULL,
 PRIMARY KEY (YearID),
 UNIQUE (YearName) );

CREATE TABLE Department
 (DepartmentID INT NOT NULL,
 DepartmentName VARCHAR(50) NOT NULL,
 PRIMARY KEY (DepartmentID), 
 UNIQUE (DepartmentName) );

CREATE TABLE Major
 (MajorID INT NOT NULL,
 MajorName VARCHAR(50) NOT NULL,
 Department INT NOT NULL,
 PRIMARY KEY (MajorID),
 UNIQUE (MajorName),
 FOREIGN KEY (Department) REFERENCES Department (DepartmentID) );

CREATE TABLE User
 (UserID INT NOT NULL,
 Username VARCHAR(50) NOT NULL,
 Password VARCHAR(50) NOT NULL,
 IsAdmin BOOLEAN NOT NULL,
 PRIMARY KEY (UserID),
 UNIQUE (Username) );

CREATE TABLE Student
 (StudentID INT NOT NULL,
 GTEmail VARCHAR(50) NOT NULL,
 Year INT NOT NULL,
 Major INT NOT NULL,
 PRIMARY KEY (StudentID),
 FOREIGN KEY (StudentID) REFERENCES User(UserID),
 FOREIGN KEY (Year) REFERENCES Year(YearID),
 FOREIGN KEY (Major) REFERENCES Major(MajorID) );

CREATE TABLE Category
 (CategoryID INT NOT NULL,
 CategoryName VARCHAR(50) NOT NULL,
 PRIMARY KEY (CategoryID),
 UNIQUE (CategoryName) );

CREATE TABLE Designation
 (DesignationID INT NOT NULL,
 DesignationName VARCHAR(50) NOT NULL,
 PRIMARY KEY (DesignationID),
 UNIQUE (DesignationName) );

CREATE TABLE ApplyStatus
 (ApplyStatusID INT NOT NULL,
 ApplyStatusName VARCHAR(50) NOT NULL,
 PRIMARY KEY (ApplyStatusID),
 UNIQUE (ApplyStatusName) );

CREATE TABLE Project
 (ProjectID INT NOT NULL,
 ProjectName VARCHAR(50) NOT NULL,
 AdvisorName VARCHAR(50) NOT NULL,
 AdvisorEmail VARCHAR(50) NOT NULL,
 EstimatedStudents INT NOT NULL,
 Description VARCHAR(1000) NOT NULL,
 Category VARCHAR(50) NOT NULL,
 Designation VARCHAR(50) NOT NULL,
 PRIMARY KEY (ProjectID),
 UNIQUE (ProjectName),
 FOREIGN KEY (Category) REFERENCES Category(CategoryName),
 FOREIGN KEY (Designation) REFERENCES Designation(DesignationName) );
 
CREATE TABLE ProjectMajorRestriction
 (Project INT NOT NULL,
 Major INT NOT NULL,
 PRIMARY KEY (Project, Major),
 FOREIGN KEY (Project) REFERENCES Project(ProjectID),
 FOREIGN KEY (Major) REFERENCES Major(MajorID) );

CREATE TABLE ProjectDepartmentRestriction
 (Project INT NOT NULL,
 Department INT NOT NULL,
 PRIMARY KEY (Project, Department),
 FOREIGN KEY (Project) REFERENCES Project(ProjectID),
 FOREIGN KEY (Department) REFERENCES Department(DepartmentID) );

CREATE TABLE ProjectYearRestriction
 (Project INT NOT NULL,
 Year INT NOT NULL,
 PRIMARY KEY (Project, Year),
 FOREIGN KEY (Project) REFERENCES Project(ProjectID),
 FOREIGN KEY (Year) REFERENCES Year(YearID) );

CREATE TABLE StudentProjectApplications
 (Student INT NOT NULL,
 Project INT NOT NULL,
 ApplyStatus INT NOT NULL,
 PRIMARY KEY (Student, Project),
 FOREIGN KEY (Student) REFERENCES Student(StudentID),
 FOREIGN KEY (Project) REFERENCES Project(ProjectID),
 FOREIGN KEY (ApplyStatus) REFERENCES ApplyStatus(ApplyStatusID) );

CREATE TABLE ProjectCategories
 (Project INT NOT NULL,
 Category INT NOT NULL,
 PRIMARY KEY (Project, Category),
 FOREIGN KEY (Project) REFERENCES Project(ProjectID),
 FOREIGN KEY (Category) REFERENCES Category(CategoryID) );

CREATE TABLE Course
 (CourseID INT NOT NULL,
 CourseNumber VARCHAR(50) NOT NULL,
 CourseName VARCHAR(50) NOT NULL,
 Instructor VARCHAR(50) NOT NULL, 
 EstimatedStudents INT NOT NULL,
 Category INT NOT NULL,
 Designation INT NOT NULL,
 PRIMARY KEY (CourseID),
 UNIQUE (CourseNumber, CourseName),
 FOREIGN KEY (Category) REFERENCES Category(CategoryID),
 FOREIGN KEY (Designation) REFERENCES Designation(DesignationID) );

CREATE TABLE CourseCategories
 (Course INT NOT NULL,
 Category INT NOT NULL,
 PRIMARY KEY (Course, Category),
 FOREIGN KEY (Course) REFERENCES Course(CourseID), 
 FOREIGN KEY (Category) REFERENCES Category(CategoryID) );


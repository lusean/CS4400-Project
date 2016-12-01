ALTER TABLE Majors DROP FOREIGN KEY 
Majors_ibfk_1;

ALTER TABLE Students DROP FOREIGN KEY 
Students_ibfk_1;

ALTER TABLE Students DROP FOREIGN KEY 
Students_ibfk_2; 

ALTER TABLE Students DROP FOREIGN KEY 
Students_ibfk_3;

ALTER TABLE Projects DROP FOREIGN KEY 
Projects_ibfk_1;

ALTER TABLE Projects DROP FOREIGN KEY 
Projects_ibfk_2;

ALTER TABLE Projects DROP FOREIGN KEY 
Projects_ibfk_3;

ALTER TABLE Projects DROP FOREIGN KEY 
Projects_ibfk_4;

ALTER TABLE StudentProjectApplications 
DROP FOREIGN KEY StudentProjectApplications_ibfk_1;

ALTER TABLE StudentProjectApplications 
DROP FOREIGN KEY StudentProjectApplications_ibfk_2;

ALTER TABLE StudentProjectApplications 
DROP FOREIGN KEY StudentProjectApplications_ibfk_3;

ALTER TABLE ProjectCategories 
DROP FOREIGN KEY ProjectCategories_ibfk_1;

ALTER TABLE ProjectCategories 
DROP FOREIGN KEY ProjectCategories_ibfk_2;

ALTER TABLE Courses 
DROP FOREIGN KEY Courses_ibfk_1;

ALTER TABLE CourseCategories 
DROP FOREIGN KEY CourseCategories_ibfk_1;

ALTER TABLE CourseCategories 
DROP FOREIGN KEY CourseCategories_ibfk_2;

ALTER TABLE Majors ADD CONSTRAINT Department 
FOREIGN KEY (Department) REFERENCES  Departments(DepartmentName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Students ADD CONSTRAINT Students_Username 
FOREIGN KEY (Username) REFERENCES Users(Username)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Students ADD CONSTRAINT Year 
FOREIGN KEY (Year) REFERENCES Years(YearName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Students ADD CONSTRAINT Major 
FOREIGN KEY (Major) REFERENCES Majors(MajorName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Projects ADD CONSTRAINT Designation 
FOREIGN KEY (Designation) REFERENCES Designations(DesignationName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Projects ADD CONSTRAINT MajorRestriction 
FOREIGN KEY (MajorRestriction) REFERENCES Majors(MajorName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Projects ADD CONSTRAINT YearRestriction 
FOREIGN KEY (YearRestriction) REFERENCES Years(YearName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Projects ADD CONSTRAINT DepartmentRestriction 
FOREIGN KEY (DepartmentRestriction) REFERENCES Departments(DepartmentName)
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE StudentProjectApplications ADD CONSTRAINT Student 
FOREIGN KEY (Student) REFERENCES Students(Username)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE StudentProjectApplicationsADD CONSTRAINT Student_Project_App 
FOREIGN KEY (Project) REFERENCES Projects(ProjectName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE StudentProjectApplications ADD CONSTRAINT ApplyStatus 
FOREIGN KEY (ApplyStatus) REFERENCES ApplyStatuses(ApplyStatusName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE ProjectCategories ADD CONSTRAINT Project 
FOREIGN KEY(Project) REFERENCES Projects(ProjectName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE ProjectCategories ADD CONSTRAINT Category 
FOREIGN KEY (Category) REFERENCES Categories(CategoryName)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Courses ADD CONSTRAINT Course_Designation 
FOREIGN KEY (Designation) REFERENCES Designations(DesignationName)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CourseCategories ADD CONSTRAINT Course_Categories_2 
FOREIGN KEY (Course) REFERENCES Courses(CourseNumber)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE CourseCategories ADD CONSTRAINT Category_2 
FOREIGN KEY (Category) REFERENCES Categories(CategoryName)
ON DELETE CASCADE
ON UPDATE CASCADE;
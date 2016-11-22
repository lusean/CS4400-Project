INSERT INTO ApplyStatuses VALUES ('Pending');
INSERT INTO ApplyStatuses VALUES ('Accepted');
INSERT INTO ApplyStatuses VALUES ('Rejected');

INSERT INTO Years VALUES ('Freshman');
INSERT INTO Years VALUES ('Sophomore');
INSERT INTO Years VALUES ('Junior');
INSERT INTO Years VALUES ('Senior');

INSERT INTO Departments VALUES ('Business');
INSERT INTO Departments VALUES ('Computing');
INSERT INTO Departments VALUES ('Design');
INSERT INTO Departments VALUES ('Engineering');
INSERT INTO Departments VALUES ('Liberal Arts');
INSERT INTO Departments VALUES ('Sciences');

INSERT INTO Majors VALUES ('Computational Media', 'Computing');
INSERT INTO Majors VALUES ('Computer Science', 'Computing');
INSERT INTO Majors VALUES ('Architecture', 'Design');
INSERT INTO Majors VALUES ('Industrial Design', 'Design');
INSERT INTO Majors VALUES ('Music Technology', 'Design');
INSERT INTO Majors VALUES ('Aerospace Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Biomedical Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Chemical and Biomolecular Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Civil Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Computer Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Electrical Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Environmental Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Industrial Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Materials Science and Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Mechanical Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Nuclear and Radiological Engineering', 'Engineering');
INSERT INTO Majors VALUES ('Applied Mathematics', 'Sciences');
INSERT INTO Majors VALUES ('Applied Physics', 'Sciences');
INSERT INTO Majors VALUES ('Biochemistry', 'Sciences');
INSERT INTO Majors VALUES ('Biology', 'Sciences');
INSERT INTO Majors VALUES ('Chemistry', 'Sciences');
INSERT INTO Majors VALUES ('Discrete Mathematics', 'Sciences');
INSERT INTO Majors VALUES ('Earth and Atmospheric Sciences', 'Sciences');
INSERT INTO Majors VALUES ('Physics', 'Sciences');
INSERT INTO Majors VALUES ('Psychology', 'Sciences');
INSERT INTO Majors VALUES ('Applied Language and Intercultural Studies', 'Liberal Arts');
INSERT INTO Majors VALUES ('Economics', 'Liberal Arts');
INSERT INTO Majors VALUES ('Economics and International Affairs', 'Liberal Arts');
INSERT INTO Majors VALUES ('Global Economics and Modern Languages', 'Liberal Arts');
INSERT INTO Majors VALUES ('History, Technology, and Society', 'Liberal Arts');
INSERT INTO Majors VALUES ('International Affairs', 'Liberal Arts');
INSERT INTO Majors VALUES ('International Affairs and Modern Languages', 'Liberal Arts');
INSERT INTO Majors VALUES ('Literature, Media, and Communication', 'Liberal Arts');
INSERT INTO Majors VALUES ('Public Policy', 'Liberal Arts');
INSERT INTO Majors VALUES ('Business Administration', 'Business');

INSERT INTO Designations VALUES ('Sustainable Communities');
INSERT INTO Designations VALUES ('Community');

INSERT INTO Categories VALUES ('computing for good');
INSERT INTO Categories VALUES ('doing good for your neighborhood');
INSERT INTO Categories VALUES ('reciprocal teaching and learning');
INSERT INTO Categories VALUES ('urban development');
INSERT INTO Categories VALUES ('adaptive learning');
INSERT INTO Categories VALUES ('technology for social good');
INSERT INTO Categories VALUES ('sustainable communities');
INSERT INTO Categories VALUES ('crowd-sourced');
INSERT INTO Categories VALUES ('collaborative action');

INSERT INTO Courses VALUES ('ARCH 4803', 'Green Infrastructure: EPA Campus Rainwater Challenge', 'Richard Dagenhart', 26, 'Sustainable Communities');
INSERT INTO Courses VALUES ('BMED 2250', 'Problems in Biomedical Engineering', 'Barbara Burks Fasse', 300, 'Community');
INSERT INTO Courses VALUES ('PUBP 3315', 'Environmental Policy and Politics', 'Alice Favero', 25, 'Sustainable Communities');
INSERT INTO Courses VALUES ('EAS 2803', 'Urban Forest', 'Monica Halka', 10, 'Sustainable Communities');
INSERT INTO Courses VALUES ('BIOL 1511', 'Honors Biological Principles; Honors Organismal Biology', 'Brian Hammer', 150, 'Sustainable Communities');
INSERT INTO Courses VALUES ('EAS 1600', 'Introduction to Environmental Science', 'Dana Hartley', 600, 'Community');
INSERT INTO Courses VALUES ('EAS 1601', 'Habitable Planet', 'Dana Hartley', 600, 'Community');
INSERT INTO Courses VALUES ('EAS 2750', 'Physics of the Weather', 'Dana Hartley', 30, 'Community');

INSERT INTO Courses VALUES ('LMC 2300', 'Introduction to Biomedicine and Culture', 'Anne Pollock', 30, 'Community');
INSERT INTO Courses VALUES ('LMC 3308', 'Environmentalism and Ecocriticism', 'Thomas Crawford', 30, 'Sustainable Communities');
INSERT INTO Courses VALUES ('CEE 4300', 'Environmental Engineering Systems', 'Sotira Yiacoumi', 60, 'Sustainable Communities');
INSERT INTO Courses VALUES ('CEE 4350', 'Environmental Techology in the Developing World', 'Joseph Brown', 10, 'Community');
INSERT INTO Courses VALUES ('CEE 4620', 'Environmental Impact Analysis', 'Randall L Guensler', 60, 'Sustainable Communities');
INSERT INTO Courses VALUES ('EAS 2420', 'Environmental Measures', 'Michael Eppard Chang', 30, 'Community');
INSERT INTO Courses VALUES ('CHEM 4521', 'Biophysical Chemistry', 'Bridgette Barry', 36, 'Sustainable Communities');
INSERT INTO Courses VALUES ('CHEM 4511', 'Biochemistry I', 'Raquel Lieberman', 135, 'Community');
INSERT INTO Courses VALUES ('CHEM 4740', 'Atmospheric Chemistry','Dana E Hartley', 18, 'Sustainable Communities');
INSERT INTO Courses VALUES ('PHIL 3109', 'Engineering Ethics', 'Robert Rosenberger', 30, 'Community');
INSERT INTO Courses VALUES ('PHIL 3127', 'Science, Technology, and Human Values', 'Robert Rosenberger', 30, 'Community');
INSERT INTO Courses VALUES ('NRE 4404', 'Radiation Assessment and Waste Management', 'Paul Charp', 30, 'Sustainable Communities'); 


INSERT INTO CourseCategories VALUES ('ARCH 4803', 'computing for good');
INSERT INTO CourseCategories VALUES ('ARCH 4803', 'doing good for your neighborhood');
INSERT INTO CourseCategories VALUES ('BMED 2250', 'computing for good');
INSERT INTO CourseCategories VALUES ('BMED 2250', 'doing good for your neighborhood');
INSERT INTO CourseCategories VALUES ('PUBP 3315', 'urban development' );
INSERT INTO CourseCategories VALUES ('PUBP 3315', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('EAS 2803', 'urban development');
INSERT INTO CourseCategories VALUES ('EAS 2803', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('BIOL 1511', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('EAS 1600', 'urban development' );
INSERT INTO CourseCategories VALUES ('EAS 1600', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('EAS 1601', 'urban development');
INSERT INTO CourseCategories VALUES ('EAS 1601', 'sustainable communities');

INSERT INTO CourseCategories VALUES ('LMC 2300', 'collaborative action');
INSERT INTO CourseCategories VALUES ('LMC 3308', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('CEE 4300', 'collaborative action');
INSERT INTO CourseCategories VALUES ('CEE 4300', 'technology for social good');
INSERT INTO CourseCategories VALUES ('CEE 4350', 'technology for social good');
INSERT INTO CourseCategories VALUES ('CEE 4350', 'urban development');
INSERT INTO CourseCategories VALUES ('CEE 4620', 'doing good for your neighborhood');
INSERT INTO CourseCategories VALUES ('CEE 4620', 'computing for good');
INSERT INTO CourseCategories VALUES ('EAS 2420', 'collaborative action'); 
INSERT INTO CourseCategories VALUES ('CHEM 4521', 'reciprocal teaching and learning');
INSERT INTO CourseCategories VALUES ('CHEM 4511', 'sustainable communities');
INSERT INTO CourseCategories VALUES ('CHEM 4740', 'collaborative action');
INSERT INTO CourseCategories VALUES ('CHEM 4740', 'doing good for your neighborhood');
INSERT INTO CourseCategories VALUES ('PHIL 3109', 'doing good for your neighborhood');
INSERT INTO CourseCategories VALUES ('PHIL 3127', 'reciprocal teaching and learning');
INSERT INTO CourseCategories VALUES ('NRE 4404', 'sustainable communities');

INSERT INTO Projects VALUES ('Excel Peer Support Network', 'Marnie Williams', 'mw@gatech.edu', 60, 'Excel (www.excel.gatech.edu) is a four-year, dual certificate program for students with intellectual and developmental disabilities. The Peer Support Network is designed to provide the individualized support necessary for Excel students to thrive at Georgia Tech.', 'Community', 'Computer Science', 'Senior', NULL);
INSERT INTO Projects VALUES ('ESW Hydroponics/Urban Farming Project', 'Nicole Kinnard', 'nk@gatech.edu', 7, 'The Hydroponics/Urban Farming Project experiments with different ways to grow produce in urban areas using limited space and water resources. We investigate both soil-based and hydroponic methods of growing in order to find the most efficient, economically viable, and environmentally sustainable way to grow produce in Atlanta.', 'Sustainable Communities', NULL, 'Junior', NULL);
INSERT INTO Projects VALUES ('Excel Current Events', 'Ashley Bidlack', 'ab@gatech.edu', 15, 'Excel Current Events is a participation (not for credit) course for degree-seeking students who are interested in developing their communication skills in conversations with adults with intellectual and developmental disabilities.', 'Community', NULL, 'Senior', 'Computing');
INSERT INTO Projects VALUES ('Shakespeare in Prison Project', 'Sarah Higinbotham', 'sh@gatech.edu', 20, 'As the world celebrates the 400th anniversary of Shakespeare’s death in 2016, Georgia Tech students will travel to a high-security men’s prison outside Atlanta to discuss Shakespeare with incarcerated students.', 'Community', NULL, NULL, 'Design');
INSERT INTO Projects VALUES ('Know Your Water Project', 'Neha Kumar', 'nk2@gatech.edu', 10, 'This project will allow students to be part of a large, crowd-sourced study – at little cost to themselves – to contribute to a knowledge bank of how different communities treat and track their water quality. If you are interested in participating in this study, please let us know.', 'Sustainable Communities', 'Computer Science', 'Senior', NULL);
INSERT INTO Projects VALUES ('Epic Intentions', 'Yeji Lee', 'yl@gatech.edu', 20, 'Epic Intentions connects an interdisciplinary team of students with a local nonprofit to apply technical skills for social and civic good to help make the nonprofits make a greater impact in the community.', 'Community', NULL, NULL, NULL);

INSERT INTO ProjectCategories VALUES ('Excel Peer Support Network', 'computing for good');
INSERT INTO ProjectCategories VALUES ('Excel Peer Support Network', 'doing good for your neighborhood');
INSERT INTO ProjectCategories VALUES ('Excel Peer Support Network', 'reciprocal teaching and learning');
INSERT INTO ProjectCategories VALUES ('ESW Hydroponics/Urban Farming Project', 'urban development');
INSERT INTO ProjectCategories VALUES ('ESW Hydroponics/Urban Farming Project', 'sustainable communities');
INSERT INTO ProjectCategories VALUES ('Excel Current Events', 'computing for good');
INSERT INTO ProjectCategories VALUES ('Excel Current Events', 'doing good for your neighborhood');
INSERT INTO ProjectCategories VALUES ('Excel Current Events', 'reciprocal teaching and learning');
INSERT INTO ProjectCategories VALUES ('Excel Current Events', 'technology for social good');
INSERT INTO ProjectCategories VALUES ('Shakespeare in Prison Project', 'urban development');
INSERT INTO ProjectCategories VALUES ('Shakespeare in Prison Project', 'sustainable communities');
INSERT INTO ProjectCategories VALUES ('Know Your Water Project', 'sustainable communities');
INSERT INTO ProjectCategories VALUES ('Know Your Water Project', 'crowd-sourced');
INSERT INTO ProjectCategories VALUES ('Epic Intentions', 'doing good for your neighborhood');
INSERT INTO ProjectCategories VALUES ('Epic Intentions', 'collaborative action');

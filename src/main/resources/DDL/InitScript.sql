CREATE DATABASE  IF NOT EXISTS restapi;
USE restapi ;

DROP TABLE IF EXISTS  studentreg ;
DROP TABLE IF EXISTS  courses ;

CREATE TABLE courses (
  courseID int NOT NULL auto_increment,
  name  varchar(45) DEFAULT NULL,
  description  varchar(500) DEFAULT NULL,
  year int DEFAULT NULL,
  semester varchar(10) DEFAULT NULL,
  instructor varchar(30) DEFAULT NULL,
  PRIMARY KEY (courseID)
);

CREATE TABLE studentreg (
  studentID int NOT NULL auto_increment ,
  name varchar(45) DEFAULT NULL,
  yearofregistration int DEFAULT NULL,
  projectgrade int DEFAULT 0,
  examgrade int DEFAULT 0,
  courseID int,
  FOREIGN KEY (courseID) 
	REFERENCES courses(courseID)
    ON DELETE CASCADE,
  PRIMARY KEY (studentID, courseID)
);

INSERT INTO courses VALUES (1,"Software Engineering","The course covers the fundamentals of software engineering, including understanding system requirements, finding appropriate engineering compromises, effective methods of design, coding, and testing, team software development, and the application of engineering tools.",2022,"summer","john");
INSERT INTO courses VALUES (2,"Software Development 2","It introduces concepts such as software processes and agile methods, and essential software development activities, from initial specification through to system maintenance. Formalisms and tools to assist in software development are also presented, including common design patterns and UML notation.",2022,"summer","john");
INSERT INTO courses VALUES (3,"Software Development 1","MYY402",2022,"summer","mary");
INSERT INTO courses VALUES (4,"Operating Systems","MYY603",2022,"summer","mary");
INSERT INTO courses VALUES (5,"Compilers","MYY802",2022,"summer","mary");

INSERT INTO studentreg VALUES (1,"Stelios Dimitriou",2018,5,7,1);
INSERT INTO studentreg VALUES (2,"Stavros Nikolaou",2019,4,8,1);
INSERT INTO studentreg VALUES (3,"Xristos Papaioannou",2021,3,4,1);
INSERT INTO studentreg VALUES (4,"Nikoletta Georgiou",2021,6,10,1);
INSERT INTO studentreg VALUES (5,"Ioanna Xristidou",2018,7,4,1);

INSERT INTO studentreg VALUES (3,"Xristos Papaioannou",2021,5,4,2);
INSERT INTO studentreg VALUES (4,"Nikoletta Georgiou",2021,1,9,2);
INSERT INTO studentreg VALUES (5,"Ioanna Xristidou",2018,10,10,2);

INSERT INTO studentreg VALUES (1,"Stelios Dimitriou",2018,4,9,3);
INSERT INTO studentreg VALUES (2,"Stavros Nikolaou",2019,2,0,3);
INSERT INTO studentreg VALUES (3,"Xristos Papaioannou",2021,5,5,3);

INSERT INTO studentreg VALUES (1,"Stelios Dimitriou",2018,6,4,4);
INSERT INTO studentreg VALUES (2,"Stavros Nikolaou",2019,9,6,4);
INSERT INTO studentreg VALUES (3,"Xristos Papaioannou",2021,2,2,4);
INSERT INTO studentreg VALUES (4,"Nikoletta Georgiou",2021,5,7,4);

INSERT INTO studentreg VALUES (1,"Stelios Dimitriou",2018,0,0,5);
INSERT INTO studentreg VALUES (2,"Stavros Nikolaou",2019,0,0,5);
INSERT INTO studentreg VALUES (3,"Xristos Papaioannou",2021,0,0,5);
INSERT INTO studentreg VALUES (4,"Nikoletta Georgiou",2021,0,0,5);
INSERT INTO studentreg VALUES (5,"Ioanna Xristidou",2018,0,0,5);
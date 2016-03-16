# Linear Programming Recommendation System 

- This software is used for student course preference recommendations. 
- Build on MAC OS 10.10.5, Java 8, Eclipse Luna J2EE edition. 
- Use PostgreSQL 9.4 database at localhost:5432, and Apache Tomcat 8 as the server.

### The step of compilation and running:
1, Import the package "webapp" into the Eclipse workspace.

2, Open the J2EE perspective of Eclipse, and check if the JDBC and PostgreSQL Driver .jar have been on the build path 

3, Create a new database called "project4" in PostgreSQL, at localhost:5432. Use "postgres" and "project4" as the username and pin by default. 

4, Run the project "DataFeeder" to automatically generate the database filled by dummy data for testing and development. The main entrance of the program is in the test folder under "DataFeeder".

5, Configure the Apache Tomcat 8 as the linked server in Eclipse. 

6, Open the ComputingCore and CourseRecSys projects both. Right click on the file "index.jsp" under the folder WebContent in the project CourseRecSys, run this file as Java Server APP. The login page of the software will show up. 

7, There are in fact 5000 student records in the database already. You can check the password and username in the "Student" table in the database. 

8, The pin of admin mode by default is "0001" as username and "admin123" as the pin. 

/**
 * @author yuwang
 * 
 * The class for generating a lot of dummy data for testing and development purpose 
 * Main entrance 
 *
 */

package DataFeeder;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class DataFeeder {

	public DataFeeder() {
		super();

	}

	public void generateDataForTesting(String dbpath, String user, String password, int num) {

		(new Program(dbpath, user, password)).createProgram();
		(new Specialty(dbpath, user, password)).createSpecialties();
		try {
			(new Specialty(dbpath, user, password)).insertCourses("res/specialty_courses_cpr.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			(new Specialty(dbpath, user, password)).insertCourses("res/specialty_courses_cs.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			(new Specialty(dbpath, user, password)).insertCourses("res/specialty_courses_ii.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			(new Specialty(dbpath, user, password)).insertCourses("res/specialty_courses_ml.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		(new Course(dbpath, user, password)).createCourse();
		try {
			(new Course(dbpath, user, password)).insertCourses("res/courses.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		(new Student(dbpath, user, password)).createStudent();

		(new Student(dbpath, user, password)).insertStudents(num);

		(new Grade(dbpath, user, password)).createGrade();

		(new Grade(dbpath, user, password)).insertGrade(num);

		(new Admin(dbpath, user, password)).createAdmin();
		try {
			(new Admin(dbpath, user, password)).insertAdmins();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		(new Login(dbpath, user, password)).createLogin();
		try {
			(new Login(dbpath, user, password)).insertLogins();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		(new Task(dbpath, user, password)).createTask();
		try {
			(new Task(dbpath, user, password)).insertTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

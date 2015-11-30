/**
 * 
 */
package Servelet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Computing.DatabaseConnection;

/**
 * @author yuwang
 *
 */
public class AdminDashboard {

	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	int totalStudent;
	int totalCourse;
	int totalOpenCourse;
	int totalSpecialty;
	int totalComputing;
	int lastRecCap;

	public AdminDashboard() {
		totalStudent = obtainStudentNumber();
		totalCourse = obtainCourseNumber();
		totalOpenCourse = obtainOpenCourseNumber();
		totalSpecialty = obtainSpecialtyNumber();
		totalComputing = obtainComputingNumber();
		lastRecCap = obtainLastCap();

	}

	private int obtainLastCap() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT task.size FROM public.task ORDER BY task.time ASC LIMIT 1 ;");
			while (rs.next()) {
				i = rs.getInt("size");
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int obtainStudentNumber() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student.id FROM public.student;");
			while (rs.next()) {
				i++;
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int obtainCourseNumber() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.num FROM public.course;");
			while (rs.next()) {
				i++;
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int obtainOpenCourseNumber() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id FROM public.course WHERE course.isopen=1;");
			while (rs.next()) {
				i++;
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int obtainSpecialtyNumber() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT program.specialty FROM public.program;");
			while (rs.next()) {
				i++;
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int obtainComputingNumber() {
		int i = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT task.size FROM public.task;");
			while (rs.next()) {
				i++;
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return i;

	}

	public int getLastRecCap() {
		return lastRecCap;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public int getTotalCourse() {
		return totalCourse;
	}

	public int getTotalOpenCourse() {
		return totalOpenCourse;
	}

	public int getTotalSpecialty() {
		return totalSpecialty;
	}

	public int getTotalComputing() {
		return totalComputing;
	}

}

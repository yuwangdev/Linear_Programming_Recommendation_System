/**
 * 
 */
package Servelet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.sun.org.apache.regexp.internal.recompile;

import Computing.*;

/**
 * @author yuwang
 *
 */
public class CourseServ {
	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	HashMap<Integer, String> getCourseLabelNumMapping() {
		HashMap<Integer, String> result = new HashMap<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.num, course.id FROM public.course;");

			while (rs.next()) {
				result.put(rs.getInt("num"), rs.getString("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return result;

	}

	private HashMap<String, String> getCourseFullNameAbbreMapping() {
		HashMap<String, String> result = new HashMap<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id, course.name FROM public.course;");

			while (rs.next()) {
				result.put(rs.getString("id"), rs.getString("name"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return result;

	}

	public String getCourseAbbre(int cid) {

		return getCourseLabelNumMapping().get(cid);
	}

	public String getCourseFullName(String abbre) {

		return getCourseFullNameAbbreMapping().get(abbre);
	}

	public String getTextPrio(int p) {
		String r = "";
		switch (p) {
		case 0:
			r = "Low";
			break;
		case 1:
			r = "Normal";
			break;
		case 2:
			r = "High";
			break;
		case 3:
			r = "Urgent";
			break;
		}

		return r;
	}

}

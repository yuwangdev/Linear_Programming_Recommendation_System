/**
 * 
 */
package Computing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author yuwang
 * 
 *         Helper class for db CRUD actions
 *
 */
public class DbHelperForCore {

	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	// Tested
	public static void putThisCoursePreferenceIntoDb(int studentId, HashMap<String, Integer> coursePreference) {

		String time = InputDataCache.timeStamp;
		String pc = "{"; // pc: prefered courses list
		String pr = "{"; // pr: priority of each
		int i = 0;
		// System.out.println(coursePreference.size());
		for (String key : coursePreference.keySet()) {
			if (i != coursePreference.size() - 1) {
				pc = pc + '"' + key + '"' + ", ";

				pr = pr + coursePreference.get(key).toString() + ", ";
			} else {
				pc = pc + '"' + key + '"' + "}";
				pr = pr + coursePreference.get(key).toString() + "}";
			}
			i++;

		}

		pc = "'" + pc + "'";
		pr = "'" + pr + "'";

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			String sql = "INSERT INTO TASK (TIME, ID, PCOURSE, PRIO) " + "VALUES (" + time + ", " + studentId + ", "
					+ pc + ", " + pr + ");";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	// Tested
	public static void AddCoursePreferenceIntoDbOnDemand(HashMap<String, Integer> coursePreference) {
		for (String k : coursePreference.keySet()) {
			Connection c = null;
			Statement stmt = null;
			int cd = 0;
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection(dbpath, user, password);

				stmt = c.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT course.demand FROM public.course WHERE course.id=" + "'" + k + "'" + ";");
				while (rs.next()) {
					cd = Integer.parseInt(rs.getString("demand").toString());
					// cd: current demand number of each course
				}

				cd++;

				stmt.executeUpdate("UPDATE public.course SET demand=" + cd + " WHERE course.id=" + "'" + k + "'" + ";");

				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}

		}

	}

	// Tested
	public static ArrayList<String> loadOpenCourseListFromDb() {
		ArrayList<String> result = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id FROM public.course WHERE course.isopen=1;");
			while (rs.next()) {
				result.add(rs.getString("id"));

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

	// Tested
	public static int calcualteMaxCourseNumber(int id, int semesterId) {

		int r = 0; // r is the result
		if ((new Helper()).ifMeetFunda((new Helper()).getTakenCourseList(Integer.toString(id)))) {
			if (semesterId != 2 && semesterId != 5 && semesterId != 8 && semesterId != 11 && semesterId != 14
					&& semesterId != 17) {
				r = 3;
			} else {
				r = 1;
			}

		}

		if (!(new Helper()).ifMeetFunda((new Helper()).getTakenCourseList(Integer.toString(id)))) {
			if (semesterId != 2 && semesterId != 5 && semesterId != 8 && semesterId != 11 && semesterId != 14
					&& semesterId != 17) {
				r = 2;
			} else {
				r = 1;
			}

		}

		return r;

	}

	// Tested
	public static HashMap<Integer, String[]> loadEntirePreferenceListFromDb() {
		HashMap<Integer, String[]> result = new HashMap<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select t.id, t.pcourse from public.task t inner join (select id, max(time) as MaxTime from public.task group by id ) tm on t.id = tm.id and t.time = tm.MaxTime;");
			while (rs.next()) {
				result.put(Integer.parseInt(rs.getString("id")), (String[]) rs.getArray("pcourse").getArray());
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

	// Tested
	public static int calcualteTotalPreferedStudentNumber() {
		return loadEntirePreferenceListFromDb().size();

	}

	// Tested
	public static HashMap<String, Integer> makeCourseLabelNumberMapping() {

		HashMap<String, Integer> result = new HashMap<>();

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id, course.num FROM public.course WHERE course.isopen=1;");

			while (rs.next()) {

				result.put(rs.getString("id").toString(), Integer.parseInt(rs.getString("num").toString()));

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

	// Tested
	public static ArrayList<ArrayList<String>> convertToArrayList(HashMap<Integer, String[]> entirePreference) {
		ArrayList<ArrayList<String>> result = new ArrayList<>();

		for (int key : entirePreference.keySet()) {
			ArrayList<String> t = new ArrayList<>();
			for (int i = 0; i < entirePreference.get(key).length; i++) {
				t.add(entirePreference.get(key)[i]);
			}
			result.add(t);

		}

		return result;
	}

	// Tested
	public static ArrayList<ArrayList<Integer>> getEnumFinalPrefCourseList(ArrayList<ArrayList<String>> c,
			HashMap<String, Integer> m) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for (int i = 0; i < c.size(); i++) {
			ArrayList<Integer> t = new ArrayList<>();
			for (int j = 0; j < c.get(i).size(); j++) {

				t.add(m.get(c.get(i).get(j)));
			}

			result.add(t);

		}

		return result;
	}

	// Tested
	public static int getAv() {
		ArrayList<Integer> t = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.demand FROM public.course;");

			while (rs.next()) {

				t.add(rs.getInt("demand"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		int s = 0;
		for (int i = 0; i < t.size(); i++) {
			s = s + t.get(i);

		}

		return s / t.size();

	}

	public static int getRD() {
		int x = 0;

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT task.size FROM public.task WHERE time =" + InputDataCache.timeStamp + ";");

			while (rs.next()) {

				x = rs.getInt("size");

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return x;
	}

}

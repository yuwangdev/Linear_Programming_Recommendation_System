package Computing;
/**
 * @author yuwang
 * 
 * Post treatment to get final suggested course list and comments 
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class postTreatment {

	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	private int studentId;

	private int r;
	// r is the raw course capacity calculated by gurobi
	private HashMap<String, Integer> coursePreference;
	private ArrayList<String> finalRecLists;
	private String mark;
	private String timeStamp;

	// Tested
	public postTreatment(String timeStamp, int studentId, int rawComputedCapacity,
			HashMap<String, Integer> coursePreference) {
		super();
		this.studentId = studentId;
		this.r = rawComputedCapacity;
		this.coursePreference = coursePreference;
		this.timeStamp = timeStamp;
	}

	// Tested
	public void doPostTreat() {

		ArrayList<String> r = new ArrayList<>();
		String m = "";
		ArrayList<String> c = new ArrayList<>();
		this.r = DbHelperForCore.getAv();
		ArrayList<Integer> p = new ArrayList<>();
		for (String k : this.coursePreference.keySet()) {
			c.add(k);
			p.add(this.coursePreference.get(k));
		}

		// compare the preference to the real course seat and preset seat number
		for (int i = 0; i < c.size(); i++) {

			int pre = getPreSeat(c.get(i));
			int opt = this.r;
			int cd = getCurrentDemand(c.get(i));
			Boolean add = true;

			if (p.get(i) == 3) {
				if (cd <= pre) {

					add = true;
					m = m + " " + c.get(i).toString() + " (Urgent) has " + Integer.toString((pre - cd))
							+ " room for selection; ";
				}
				if (pre < cd) {
					add = true;
					m = m + " " + c.get(i).toString() + " (Urgent) has NO room for selection ("
							+ Integer.toString((cd - pre)) + "), but is still in the list due to urgent prioirity;";
				}
			}

			if (p.get(i) == 2) {
				if (cd < pre && pre < opt) {
					add = true;
					m = m + " " + c.get(i).toString() + " (High) has " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (cd < opt && opt < pre) {
					add = true;
					m = m + " " + c.get(i).toString() + " (High) may have " + Integer.toString((opt - cd)) + " to "
							+ Integer.toString((pre - cd)) + " room for selection;";
				}
				if (opt < pre && pre < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (High) has NO room for selection ("
							+ Integer.toString((cd - pre)) + ");";
				}
				if (opt < cd && cd < pre) {
					add = true;
					m = m + " Be Cautious:" + c.get(i).toString() + " (High) may at worst NOT have "
							+ Integer.toString((cd - opt)) + " room, or at best " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (pre < opt && opt < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (High) may NOT have " + Integer.toString((cd - pre))
							+ " room at worst, or at best " + Integer.toString((cd - opt)) + " room;";
				}
				if (pre < cd && cd < opt) {
					add = true;
					m = m + " Be Cautious:" + c.get(i).toString() + " (High) may at worst NOT have "
							+ Integer.toString((cd - pre)) + " room, or at best " + Integer.toString((opt - cd))
							+ " room for selection;";
				}

			}

			if (p.get(i) == 1) {
				if (cd < pre && pre < opt) {
					add = true;
					m = m + " " + c.get(i).toString() + " (Normal) has " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (cd < opt && opt < pre) {
					add = true;
					m = m + " " + c.get(i).toString() + " (Normal) may have " + Integer.toString((opt - cd)) + " to "
							+ Integer.toString((pre - cd)) + " room for selection;";
				}
				if (opt < pre && pre < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) has NO room for selection ("
							+ Integer.toString((cd - pre)) + ");";
				}
				if (opt < cd && cd < pre) {
					add = true;
					m = m + " Be Cautious:" + c.get(i).toString() + " (Normal) may at worst NOT have "
							+ Integer.toString((cd - opt)) + " room, or at best " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (pre < opt && opt < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) may NOT have " + Integer.toString((cd - pre))
							+ " room at worst, or at best " + Integer.toString((cd - opt)) + " room;";
				}
				if (pre < cd && cd < opt) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) may NOT have " + Integer.toString((cd - pre))
							+ " room at worst, or at best " + Integer.toString((opt - cd)) + " room;";
				}

			}

			if (p.get(i) == 0) {
				if (cd < pre && pre < opt) {
					add = true;
					m = m + " " + c.get(i).toString() + " (Normal) has " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (cd < opt && opt < pre) {
					add = true;
					m = m + " " + c.get(i).toString() + " (Normal) may have " + Integer.toString((opt - cd)) + " to "
							+ Integer.toString((pre - cd)) + " room for selection;";
				}
				if (opt < pre && pre < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) has NO room for selection ("
							+ Integer.toString((cd - pre)) + ");";
				}
				if (opt < cd && cd < pre) {
					add = false;
					m = m + " Be Cautious:" + c.get(i).toString() + " (Normal) may at worst NOT have "
							+ Integer.toString((cd - opt)) + " room, or at best " + Integer.toString((pre - cd))
							+ " room for selection;";
				}
				if (pre < opt && opt < cd) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) may NOT have " + Integer.toString((cd - pre))
							+ " room at worst, or at best " + Integer.toString((cd - opt)) + " room;";
				}
				if (pre < cd && cd < opt) {
					add = false;
					m = m + " " + c.get(i).toString() + " (Normal) may NOT have " + Integer.toString((cd - pre))
							+ " room at worst, or at best " + Integer.toString((opt - cd)) + " room;";
				}

			}

			if (add) {
				r.add(c.get(i));
			}

		}

		Boolean isFunda = ifFunda(this.studentId);
		Boolean isMust = ifMust(this.studentId);
		Boolean isElec = ifElec(this.studentId);

		// check the fundamental course requirement
		if (!isFunda) {
			m = m + " Be cautious: you can only register max 2 courses! Take fundamental courses ASAP! ";
			String temp = getOneAvaiableFundaCourseThatThisStudentNeverTaken(this.studentId, this.r);
			r.add(temp);
			m = m + temp + " is advised as a fundamental course; ";
		}

		// check the speciality requirement 
		if (isFunda) {
			if (!isMust) {
				String tMust = getOneAvaiableMustCourseThatThisStudentNeverTaken(this.studentId, this.r);
				r.add(tMust);
				m = m + tMust + " is advised as a mandotary course for specialty; ";
			}

			if (isMust) {
				if (isElec) {
					m = m + " You have finished your specialty courses!; ";
				}
				if (!isElec) {
					String tElec = getOneAvaiableElecCourseThatThisStudentNeverTaken(this.studentId, this.r);
					r.add(tElec);
					m = m + tElec + " is advised as an elective course for specialty; ";

				}

			}

		}

		this.finalRecLists = r;
		this.mark = m;

		OutputDataCache.finalRecLists = this.finalRecLists;
		OutputDataCache.finalMark = this.mark;

	}

	// Tested
	public String getOneAvaiableElecCourseThatThisStudentNeverTaken(int studentId2, int rawComputedCapacity2) {
		ArrayList<String> t = new ArrayList<>();
		String x = "None";
		Connection c = null;
		Statement stmt = null;
		String spec = (new Helper()).getStudentSpe(Integer.toString(studentId2)).toLowerCase();

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + spec + ".id FROM " + spec + " WHERE must=0;");
			while (rs.next()) {
				t.add(rs.getString("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		ArrayList<String> taken = (new Helper()).getTakenCourseList(Integer.toString(studentId2));
		ArrayList<String> tmp = new ArrayList<>();
		for (int i = 0; i < t.size(); i++) {
			if (getCurrentDemand(t.get(i)) < rawComputedCapacity2 && !taken.contains(t.get(i))
					&& getCurrentDemand(t.get(i)) < getPreSeat(t.get(i))) {
				tmp.add(t.get(i));
			}
		}

		if (tmp.size() > 0) {
			x = tmp.get(0);
		}
		return x;
	}

	// Tested
	public String getOneAvaiableMustCourseThatThisStudentNeverTaken(int studentId2, int rawComputedCapacity2) {
		ArrayList<String> t = new ArrayList<>();
		String x = "None";
		Connection c = null;
		Statement stmt = null;
		String spec = (new Helper()).getStudentSpe(Integer.toString(studentId2)).toLowerCase();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM " + spec + " WHERE must=1;");
			while (rs.next()) {
				t.add(rs.getString("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		ArrayList<String> taken = (new Helper()).getTakenCourseList(Integer.toString(studentId2));
		ArrayList<String> tmp = new ArrayList<>();
		for (int i = 0; i < t.size(); i++) {
			if (getCurrentDemand(t.get(i)) < rawComputedCapacity2 && !taken.contains(t.get(i))
					&& getCurrentDemand(t.get(i)) < getPreSeat(t.get(i))) {
				tmp.add(t.get(i));
			}
		}

		if (tmp.size() > 0) {
			x = tmp.get(0);
		}
		return x;

	}

	// Tested
	public String getOneAvaiableFundaCourseThatThisStudentNeverTaken(int studentId2, int rawComputedCapacity2) {
		ArrayList<String> t = new ArrayList<>();
		String x = "None";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT course.id FROM public.course where course.isfunda=1 and  course.isopen=1;");
			while (rs.next()) {
				t.add(rs.getString("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		ArrayList<String> taken = (new Helper()).getTakenCourseList(Integer.toString(this.studentId));
		ArrayList<String> tmp = new ArrayList<>();
		for (int i = 0; i < t.size(); i++) {
			if (getCurrentDemand(t.get(i)) < this.r && !taken.contains(t.get(i))
					&& getCurrentDemand(t.get(i)) < getPreSeat(t.get(i))) {
				tmp.add(t.get(i));
			}
		}

		if (tmp.size() > 0) {
			x = tmp.get(0);
		}
		return x;

	}

	// Tested
	public Boolean ifElec(int studentId2) {

		return (new Helper()).ifElec(Integer.toString(studentId2));
	}

	// Tested
	public Boolean ifMust(int studentId2) {

		return (new Helper()).ifMust(Integer.toString(studentId2));
	}

	// Tested
	public Boolean ifFunda(int studentId2) {

		return (new Helper()).ifMeetFunda((new Helper()).getTakenCourseList(Integer.toString(studentId2)));

	}

	// Tested
	public int getCurrentDemand(String course) {
		int x = 0;
		course = "'" + course + "'";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.demand FROM public.course WHERE course.id=" + course + ";");
			while (rs.next()) {
				x = rs.getInt("demand");

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

	// Tested
	public int getPreSeat(String course) {
		int x = 0;
		course = "'" + course + "'";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT course.presetseat FROM public.course WHERE course.id=" + course + ";");
			while (rs.next()) {
				x = rs.getInt("presetseat");

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

	// Tested
	public void putRecListIntoDb() {
		String x = "{";

		for (int i = 0; i < this.finalRecLists.size(); i++) {

			if (i != this.finalRecLists.size() - 1) {
				x = x + '"' + this.finalRecLists.get(i).toString() + '"' + ", ";
			} else {
				x = x + '"' + this.finalRecLists.get(i).toString() + '"' + "}";
			}

		}

		x = "'" + x + "'";
		// System.out.println("UPDATE public.task SET rec=" + x + " WHERE
		// task.time=" + this.timeStamp + ";");
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);
			stmt = c.createStatement();
			stmt.executeUpdate("UPDATE public.task SET rec=" + x + " WHERE task.time=" + this.timeStamp + ";");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	// Tested
	public void putRecSizeIntoDb() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);
			stmt = c.createStatement();
			stmt.executeUpdate("UPDATE public.task SET size=" + this.r + " WHERE task.time=" + this.timeStamp + ";");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
}

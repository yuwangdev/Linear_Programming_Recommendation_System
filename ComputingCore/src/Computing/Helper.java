
package Computing;

/**
 * @author yuwang
 * 
 * Generic helper class for computing info visualisation 
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Helper {
	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	// Tested
	public String getStartingSemester(String sem) {
		String r = "";
		switch (Integer.parseInt(sem)) {
		case 0:
			r = "2010 Fall";
			break;
		case 1:
			r = "2011 Spring";
			break;
		case 2:
			r = "2011 Summer";
			break;
		case 3:
			r = "2011 Fall";
			break;
		case 4:
			r = "2012 Spring";
			break;
		case 5:
			r = "2012 Summer";
			break;
		case 6:
			r = "2012 Fall";
			break;
		case 7:
			r = "2013 Spring ";
			break;
		case 8:
			r = "2013 Summer";
			break;
		case 9:
			r = "2013 Fall";
			break;
		case 10:
			r = "2014 Spring";
			break;

		case 11:
			r = "2014 Summer";
			break;
		case 12:
			r = "2014 Fall";
			break;
		case 13:
			r = "2015 Spring";
			break;
		case 14:
			r = "2015 Summer";
			break;
		case 15:
			r = "2015 Fall";
			break;
		case 16:
			r = "2016 Spring";
			break;
		case 17:
			r = "2016 Summer ";
			break;

		}

		return r;
	}

	// Tested
	public String getSpecialty(String s) {
		String r = "";
		switch (s) {
		case "CPR":
			r = "Comptuer Percetion and Robotics";
			break;
		case "II":
			r = "Interactive Inteligence ";
			break;
		case "ML":
			r = "Machine Learning";
			break;
		case "CS":
			r = "Computing System";
			break;
		}

		return r;

	}

	// Tested
	public ArrayList<String> getTakenCourseList(String id) {
		ArrayList<String> x = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbpath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT grade.courseid, grade.semester, grade.grade FROM public.grade WHERE grade.studentid=" + id
							+ ";");
			while (rs.next()) {
				x.add(rs.getString("courseid").toString());

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
	public ArrayList<String> getGradeList(String id) {
		ArrayList<String> x = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbpath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT grade.grade FROM public.grade WHERE grade.studentid=" + id + ";");
			while (rs.next()) {
				x.add(rs.getString("grade").toString());

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
	public ArrayList<String> getBAboveCourseList(ArrayList<String> c, ArrayList<String> g) {
		ArrayList<String> x = new ArrayList<>();
		for (int i = 0; i < g.size(); i++) {
			if (g.get(i).equals("B") || g.get(i).equals("A")) {
				x.add(c.get(i));
			}
		}

		return x;
	}

	// Tested
	public ArrayList<String> getFundaList() {
		ArrayList<String> x = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT course.id, course.isfunda FROM public.course WHERE course.isfunda=1;");
			while (rs.next()) {
				x.add(rs.getString("id").toString());

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
	public Boolean ifMeetFunda(ArrayList<String> courseTakenLists) {
		int n = 0;
		for (int i = 0; i < getFundaList().size(); i++) {
			if (courseTakenLists.contains(getFundaList().get(i))) {
				n++;
			}
		}
		if (n >= 2) {
			return true;
		} else {
			return false;
		}

	}

	// Tested
	public double getGpa(ArrayList<String> gradeList) {

		ArrayList<Double> d = new ArrayList<>();
		for (int i = 0; i < gradeList.size(); i++) {
			d.add(numerize(gradeList.get(i)));
		}
		double sum = 0.0;
		for (int i = 0; i < d.size(); i++) {
			sum = d.get(i) + sum;
		}

		if (d.size() > 0) {
			return sum / d.size();

		} else {

			return 0.0;
		}
	}

	// Tested
	public double numerize(String i) {
		double r = 0.0;
		switch (i) {
		case "A":
			r = 4.0;
			break;
		case "B":
			r = 3.0;
			break;
		case "C":
			r = 2.0;
			break;
		case "D":
			r = 1.0;
			break;
		case "F":
			r = 0.0;
			break;
		case "W":
			r = 0.0;
			break;
		}
		return r;

	}

	// Tested
	public int getObtainedCredit(ArrayList<String> gradeList) {
		int r = 0;
		for (int i = 0; i < gradeList.size(); i++) {
			if (numerize(gradeList.get(i)) > 1.0) {
				r++;
			}
		}
		return r * 3;

	}

	// Tested
	public String textPri(int p) {
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

	// Tested
	public Boolean ifMust(String id) {
		// TODO Auto-generated method stub
		Boolean x = true;
		ArrayList<String> courseTaken = getTakenCourseList(id);
		String spe = getStudentSpe(id);
		ArrayList<String> mustSpe = getMustSpeCourseList(spe);
		int n = 0;
		for (int i = 0; i < mustSpe.size(); i++) {
			if (courseTaken.contains(mustSpe.get(i))) {
				n++;
			}
		}

		if (n == mustSpe.size()) {
			x = true;
		} else {
			x = false;
		}
		return x;
	}

	// Tested
	public ArrayList<String> getElecSpeCourseList(String spe) {
		ArrayList<String> x = new ArrayList<>();
		spe = spe.toLowerCase();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM " + spe + " WHERE must=0");
			while (rs.next()) {
				x.add(rs.getString("id"));

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
	public ArrayList<String> getMustSpeCourseList(String spe) {
		ArrayList<String> x = new ArrayList<>();
		spe = spe.toLowerCase();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM " + spe + " WHERE must=1");
			while (rs.next()) {
				x.add(rs.getString("id"));

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
	public String getStudentSpe(String id) {
		String x = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT student.specialty FROM public.student WHERE student.id=" + id + ";");
			while (rs.next()) {
				x = rs.getString("specialty");

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
	public Boolean ifElec(String id) {
		Boolean x = true;
		ArrayList<String> courseTaken = getTakenCourseList(id);
		String spe = getStudentSpe(id);
		int req = getElecNumber(spe);
		ArrayList<String> elecSpe = getElecSpeCourseList(spe);
		int n = 0;
		for (int i = 0; i < courseTaken.size(); i++) {
			if (elecSpe.contains(courseTaken.get(i))) {
				n++;
			}
		}

		if (n >= req) {
			x = true;
		} else {
			x = false;
		}
		return x;
	}

	// Tested
	public int getElecNumber(String spe) {
		// TODO Auto-generated method stub
		// SELECT program.elecnum FROM public.program WHERE specialty='CS';
		int x = 0;
		spe = "'" + spe + "'";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT program.elecnum FROM public.program WHERE specialty=" + spe + ";");
			while (rs.next()) {
				x = rs.getInt("elecnum");
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

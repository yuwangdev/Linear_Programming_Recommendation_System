package DataFeeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Random;

public class Grade {
	private String dbPath;
	private String user;
	private String password;

	public Grade(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createGrade() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE GRADE " + "(STUDENTID INT, COURSEID TEXT, SEMESTER INT, GRADE TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("GRADE Table created successfully");

	}

	public void insertGrade(int t) {
		// TODO Auto-generated method stub
		// t: total amount of entry records
		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<String> course = new ArrayList<>();
		ArrayList<Integer> sem = new ArrayList<>();
		ArrayList<String> gr = new ArrayList<>();
		ArrayList<String> cl = getList();

		for (int i = 0; i < t; i++) {

			for (int j = 0; j < (new Random()).nextInt(11); j++) {
				id.add(260510650 + i);
				sem.add((new Random()).nextInt(17));
				String g = "";
				switch ((new Random()).nextInt(6)) {
				case 0:
					g = "A";
					break;
				case 1:
					g = "B";
					break;
				case 2:
					g = "C";
					break;
				case 3:
					g = "D";
					break;
				case 4:
					g = "F";
					break;
				case 5:
					g = "W";
					break;
				}
				gr.add("'" + g + "'");

				course.add("'" + cl.get((new Random()).nextInt(63)) + "'");

			}

		}

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();

			for (int i = 0; i < id.size(); i++) {

				String sql = "INSERT INTO GRADE (STUDENTID, COURSEID, SEMESTER, GRADE) " + "VALUES (" + id.get(i) + ", "
						+ course.get(i) + ", " + sem.get(i) + ", " + gr.get(i) + ");";
				stmt.executeUpdate(sql);

			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Student Tables updated successfully");

	}

	private ArrayList<String> getList() {
		// TODO Auto-generated method stub
		ArrayList<String> cl = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id FROM public.course;");
			while (rs.next()) {
				cl.add(rs.getString("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return cl;
	}
}

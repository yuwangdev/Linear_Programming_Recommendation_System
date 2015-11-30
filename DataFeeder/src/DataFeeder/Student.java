package DataFeeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Random;

public class Student {

	private String dbPath;
	private String user;
	private String password;

	public Student(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createStudent() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE STUDENT "
					+ "(ID INT, PASSWORD TEXT, EMAIL TEXT, NAME TEXT, STARTING INT, SPECIALTY TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Student General Table created successfully");

	}

	public void insertStudents(int t) {
		// t is the total student, unique

		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<String> psw = new ArrayList<>();
		ArrayList<String> email = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		ArrayList<Integer> st = new ArrayList<>();
		ArrayList<String> sp = new ArrayList<>();

		for (int i = 0; i < t; i++) {
			id.add(260510650 + i);
			psw.add("'" + "psw" + Integer.toString(i) + "'");
			name.add("'" + "Name" + Integer.toString(i) + "'");
			email.add("'" + "Name" + Integer.toString(i) + "@course.edu" + "'");
			st.add((new Random().nextInt(17)));
			String s = "";
			Random r = new Random();
			switch (r.nextInt(4)) {
			case 0:
				s = "CPR";
				break;
			case 1:
				s = "II";
				break;
			case 2:
				s = "CS";
				break;
			case 3:
				s = "ML";
				break;
			}
			sp.add("'" + s + "'");

		}

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();

			for (int i = 0; i < id.size(); i++) {

				String sql = "INSERT INTO STUDENT (ID, PASSWORD, EMAIL, NAME, STARTING, SPECIALTY) " + "VALUES ("
						+ id.get(i) + ", " + psw.get(i) + ", " + email.get(i) + ", " + name.get(i) + ", " + st.get(i)
						+ ", " + sp.get(i) + ");";
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

}

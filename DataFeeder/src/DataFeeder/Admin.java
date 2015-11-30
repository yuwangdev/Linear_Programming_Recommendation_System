package DataFeeder;

import java.sql.*;
import java.util.ArrayList;

public class Admin {
	private String dbPath;
	private String user;
	private String password;

	public Admin(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createAdmin() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE ADMIN " + "(ID INT, PASSWORD TEXT, EMAIL TEXT, NAME TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("ADMIN General Table created successfully");

	}

	public void insertAdmins() {
		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<String> psw = new ArrayList<>();
		ArrayList<String> email = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			id.add(10000 + i);
			psw.add("'" + "psw" + Integer.toString(i) + "'");
			name.add("'" + "Name" + Integer.toString(i) + "'");
			email.add("'" + "Name" + Integer.toString(i) + "@course.edu" + "'");
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();

			for (int i = 0; i < id.size(); i++) {

				String sql = "INSERT INTO ADMIN (ID, PASSWORD, EMAIL, NAME) " + "VALUES (" + id.get(i) + ", "
						+ psw.get(i) + ", " + email.get(i) + ", " + name.get(i) + ");";
				stmt.executeUpdate(sql);

			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("ADMIN Tables updated successfully");

	}

}

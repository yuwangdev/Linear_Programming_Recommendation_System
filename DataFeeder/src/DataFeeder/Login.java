package DataFeeder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Login {
	private String dbPath;
	private String user;
	private String password;

	public Login(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createLogin() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE LOGIN " + "(ID INT, TIME TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("LOGIN General Table created successfully");

	}

	public void insertLogins() {
		// TODO Auto-generated method stub
		List<Integer> id = getdata(200);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			String time = "";

			for (int i = 0; i < id.size(); i++) {
				for (int j = 0; j < (new Random().nextInt(4)); j++) {

					time = "'" + (new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")).format(new Date()) + "'";

					String sql = "INSERT INTO LOGIN (ID, TIME) " + "VALUES (" + id.get(i) + ", " + time + ");";
					stmt.executeUpdate(sql);
				}

			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("LOGIN Tables updated successfully");

	}

	private List<Integer> getdata(int i) {
		// TODO Auto-generated method stub
		List<Integer> cl = new ArrayList<>();

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student.id FROM public.student;");

			while (rs.next()) {
				cl.add(rs.getInt("id"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return cl.subList(0, i);
	}

}

package DataFeeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Task {

	private String dbPath;
	private String user;
	private String password;

	public Task(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createTask() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			String sql = "CREATE TABLE TASK " + "(TIME TEXT, ID INT, PCOURSE TEXT[], PRIO INT[], REC TEXT[], SIZE INT)";
			stmt.executeUpdate(sql);

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("TASK Table created successfully");

	}

	public void insertTasks() {
		// TODO Auto-generated method stub
		ArrayList<String> time = new ArrayList<>();
		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<ArrayList<String>> pref = new ArrayList<>();
		ArrayList<ArrayList<Integer>> pri = new ArrayList<>();
		ArrayList<ArrayList<String>> rec = new ArrayList<>();
		ArrayList<Integer> size = new ArrayList<>();

		for (int i = 0; i < getStudata().size(); i++) {

			for (int j = 0; j < (new Random()).nextInt(6); j++) {
				time.add("'" + (new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")).format(new Date()) + "'");
				id.add(getStudata().get(i));
				size.add((new Random()).nextInt(50) + 100);

				ArrayList<String> preftemp = new ArrayList<>();
				preftemp.add(getc().get((new Random()).nextInt(getc().size())));
				preftemp.add(getc().get((new Random()).nextInt(getc().size())));
				preftemp.add(getc().get((new Random()).nextInt(getc().size())));
				preftemp.add(getc().get((new Random()).nextInt(getc().size())));
				pref.add(preftemp);

				ArrayList<Integer> pritemp = new ArrayList<>();
				pritemp.add((new Random()).nextInt(4));
				pritemp.add((new Random()).nextInt(4));
				pritemp.add((new Random()).nextInt(4));
				pritemp.add((new Random()).nextInt(4));

				pri.add(pritemp);

				ArrayList<String> rectemp = new ArrayList<>();
				rectemp.add(getc().get((new Random()).nextInt(getc().size())));
				rectemp.add(getc().get((new Random()).nextInt(getc().size())));
				rectemp.add(getc().get((new Random()).nextInt(getc().size())));
				rectemp.add(getc().get((new Random()).nextInt(getc().size())));
				rec.add(rectemp);

			}

		}

		// for (int i = 0; i < id.size(); i++) {
		// System.out.println(time.get(i));
		// System.out.println(id.get(i));
		// System.out.println(size.get(i));
		// for (int j = 0; j < pref.get(i).size(); j++) {
		// System.out.println(pref.get(i).get(j));
		// System.out.println(pri.get(i).get(j));
		// System.out.println(rec.get(i).get(j));
		//
		// }
		//
		// }

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();

			for (int i = 0; i < id.size(); i++) {

				String ti = "'" + time.get(i) + "'";
				String pc = "{";
				String pr = "{";
				String re = "{";

				for (int j = 0; j < pref.get(i).size(); j++) {
					if (j != pref.get(i).size() - 1) {
						pc = pc + '"' + pref.get(i).get(j).toString() + '"' + ", ";

					} else {
						pc = pc + '"' + pref.get(i).get(j).toString() + '"' + "}";
					}

				}
				pc = "'" + pc + "'";

				for (int j = 0; j < pri.get(i).size(); j++) {
					if (j != pri.get(i).size() - 1) {
						pr = pr + pri.get(i).get(j).toString() + ", ";

					} else {
						pr = pr + pri.get(i).get(j).toString() + "}";
					}

				}
				pr = "'" + pr + "'";

				for (int j = 0; j < rec.get(i).size(); j++) {
					if (j != rec.get(i).size() - 1) {
						re = re + '"' + rec.get(i).get(j).toString() + '"' + ", ";

					} else {
						re = re + '"' + rec.get(i).get(j).toString() + '"' + "}";
					}

				}
				re = "'" + re + "'";

				String sql = "INSERT INTO TASK (TIME, ID, PCOURSE, PRIO, REC, SIZE) " + "VALUES (" + time.get(i) + ", "
						+ id.get(i) + ", " + pc + ", " + pr + ", " + re + ", " + size.get(i) + ");";

				stmt.executeUpdate(sql);

			}

			stmt.close();
			System.out.println("TASK TABLE updated!");
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	private ArrayList<String> getc() {
		// TODO Auto-generated method stub
		ArrayList<String> cl = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.id FROM public.course WHERE course.isopen=1;");
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

	private List<Integer> getStudata() {
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

		return cl.subList(0, 1500);
	}

}

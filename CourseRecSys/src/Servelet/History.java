/**
 * 
 */
package Servelet;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Computing.DatabaseConnection;

/**
 * @author yuwang
 *
 */
public class History {
	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	private ArrayList<String> time = new ArrayList<>();

	public ArrayList<String[]> pref = new ArrayList<>();
	public ArrayList<Integer[]> pri = new ArrayList<>();
	public ArrayList<String[]> rec = new ArrayList<>();

	private String id;

	public History(String id) {
		super();
		this.id = id;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public ArrayList<String[]> getPref() {
		return pref;
	}

	public ArrayList<Integer[]> getPri() {
		return pri;
	}

	public ArrayList<String[]> getRec() {

		return rec;
	}

	public void dig() {
		ArrayList<String> x = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbpath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT task.time, task.pcourse, task.prio, task.rec, task.size FROM public.task WHERE task.id="
							+ this.id + ";");

			while (rs.next()) {
				if (rs.getArray("rec") != null && rs.getString("time") != null && rs.getArray("pcourse") != null
						&& rs.getString("time") != null) {

					this.rec.add((String[]) rs.getArray("rec").getArray());
					this.time.add(rs.getString("time").toString());

					this.pref.add((String[]) rs.getArray("pcourse").getArray());
					this.pri.add((Integer[]) rs.getArray("prio").getArray());
				}

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

}

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

import Computing.*;

/**
 * @author yuwang
 *
 */
public class OpenCourseList {
	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	private ArrayList<Integer> num = new ArrayList<>();

	private ArrayList<String> cid = new ArrayList<>();
	private ArrayList<String> funda = new ArrayList<>();
	private ArrayList<String> prof = new ArrayList<>();
	private ArrayList<String> ta = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<Integer> cd = new ArrayList<>();
	private ArrayList<Integer> ps = new ArrayList<>();

	public OpenCourseList() {
		super();

	}

	public ArrayList<Integer> getNum() {
		return num;
	}

	public ArrayList<String> getCid() {
		return cid;
	}

	public ArrayList<String> getFunda() {
		return funda;
	}

	public ArrayList<String> getProf() {
		return prof;
	}

	public ArrayList<String> getTa() {
		return ta;
	}

	public ArrayList<String> getName() {
		return name;
	}

	public ArrayList<Integer> getCd() {
		return cd;
	}

	public ArrayList<Integer> getPs() {
		return ps;
	}

	public void dig() {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbpath, this.user, this.password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT course.num, course.id, course.isfunda, course.name, course.ta, course.prof, course.demand, course.presetseat FROM public.course WHERE course.isopen=1;");

			while (rs.next()) {
				this.num.add(rs.getInt("num"));
				this.cid.add(rs.getString("id"));
				if (rs.getInt("isfunda") == 1) {
					this.funda.add("Yes");

				} else {
					this.funda.add("No");
				}
				this.name.add(rs.getString("name"));
				this.ta.add(rs.getString("ta"));
				this.prof.add(rs.getString("prof"));
				this.cd.add(rs.getInt("demand"));
				this.ps.add(rs.getInt("presetseat"));

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

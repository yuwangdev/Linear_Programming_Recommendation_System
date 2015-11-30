package DataFeeder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 * 
 */

/**
 * 
 * 
 * @author yuwang
 *
 */
public class Program {

	private String dbPath;
	private String user;
	private String password;

	public Program(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createProgram() {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE PROGRAM " + "(SPECIALTY TEXT, MUSTNUM INT, ELECNUM INT)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO PROGRAM (SPECIALTY, MUSTNUM, ELECNUM) " + "VALUES ('CPR', 2, 5);";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO PROGRAM (SPECIALTY, MUSTNUM, ELECNUM) " + "VALUES ('CS', 3, 3);";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO PROGRAM (SPECIALTY, MUSTNUM, ELECNUM) " + "VALUES ('II', 3, 2);";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO PROGRAM (SPECIALTY, MUSTNUM, ELECNUM) " + "VALUES ('ML', 2, 3);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Program Table created successfully");

	}

}

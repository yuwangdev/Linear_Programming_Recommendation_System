/**
 * 
 */
package Computing;

/**
 * @author yuwang
 * 
 *         Database connection class, for easy maintenance
 *
 */
public class DatabaseConnection {

	// Tested ALL
	private String dbpath = "jdbc:postgresql://localhost:5432/project4";
	private String user = "postgres";
	private String password = "admin";

	public DatabaseConnection() {
		super();

	}

	public String getDbpath() {
		return dbpath;
	}

	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

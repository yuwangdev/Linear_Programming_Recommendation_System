package DataFeeder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbpath = "jdbc:postgresql://localhost:5432/project4";  // small data
		//String dbpath = "jdbc:postgresql://localhost:5432/p4";   // big data
		
		String user = "postgres";
		String password = "admin";

		(new DataFeeder()).generateDataForTesting(dbpath, user, password, 5000);

	}

}

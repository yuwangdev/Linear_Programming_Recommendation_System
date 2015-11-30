package Computing;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDatabaseConnection {

	@Test
	public void test() {
		DatabaseConnection dbc= new DatabaseConnection();
		System.out.println(dbc.getDbpath());
		System.out.println(dbc.getPassword());
		System.out.println(dbc.getUser());
		
	}

}

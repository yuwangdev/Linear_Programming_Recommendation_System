/**
 * 
 */
package Servelet;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwang
 *
 */
public class SimpleTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		History h = new History("260510650");
		h.dig();
		for (int i = 0; i < h.getPref().size(); i++) {
			for (int j = 0; j < h.getPref().get(i).length; j++) {
				System.out.println(h.getPref().get(i)[j]);
				System.out.println(h.getPri().get(i)[j]);
			}
		}
	
		

		}

	}

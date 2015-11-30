/**
 * 
 */
package Servelet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author yuwang
 *
 */
public class AdminDashboardTest {

	@Test
	public void test() {
		AdminDashboard a = new AdminDashboard();
		System.out.println(a.getTotalComputing());
		System.out.println(a.getTotalCourse());
		System.out.println(a.getTotalOpenCourse());
		System.out.println(a.getTotalSpecialty());
		System.out.println(a.getTotalStudent());
		
	}

}

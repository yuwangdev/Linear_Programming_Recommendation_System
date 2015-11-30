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
public class CourseServTest {

	@Test
	public void test() {
		CourseServ cs = new CourseServ();
		System.out.println(cs.getCourseLabelNumMapping());
		System.out.println(cs.getCourseAbbre(12));
	}

}

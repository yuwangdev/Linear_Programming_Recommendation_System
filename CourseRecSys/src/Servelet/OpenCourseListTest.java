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
public class OpenCourseListTest {

	@Test
	public void test() {
		OpenCourseList o = new OpenCourseList();
		o.dig();
		System.out.println(o.getCd());
		System.out.println(o.getCid());
		System.out.println(o.getFunda());
		System.out.println(o.getName());
		System.out.println(o.getNum());
		System.out.println(o.getProf());
		System.out.println(o.getPs());
		System.out.println(o.getTa());
		
	}

}

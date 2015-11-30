package Computing;

import static org.junit.Assert.*;
import Computing.Helper;

import org.junit.Test;

public class TestHelper {

	@Test
	public void test() {
		Helper h = new Helper();
		String id = "260510659";
		assertEquals("2012 Spring", h.getStartingSemester("4"));
		assertEquals("Comptuer Percetion and Robotics", h.getSpecialty("CPR"));
		assertEquals(6, h.getTakenCourseList(id).size());
		System.out.println(h.getTakenCourseList(id));
		System.out.println(h.getGradeList(id));
		System.out.println(h.getBAboveCourseList(h.getTakenCourseList(id), h.getGradeList(id)));
		System.out.println(h.getFundaList());
		System.out.println(h.ifMeetFunda(h.getTakenCourseList(id)));
		System.out.println(h.getGpa(h.getGradeList(id)));
		System.out.println(h.numerize("B"));
		System.out.println(h.getObtainedCredit(h.getGradeList(id)));
		System.out.println(h.textPri(2));
		System.out.println(h.ifElec(id));
		System.out.println(h.ifMust(id));
		String spec= h.getStudentSpe(id);
		System.out.println(spec);
		System.out.println(h.getElecSpeCourseList(spec));
		System.out.println(h.getMustSpeCourseList(spec));
		System.out.println(h.getElecNumber(spec));
		
	}

}

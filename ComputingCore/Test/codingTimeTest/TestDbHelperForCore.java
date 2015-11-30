/**
 * 
 */
package codingTimeTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Computing.DbHelperForCore;

/**
 * @author yuwang
 *
 */
public class TestDbHelperForCore {

	@Test
	public void test() {

		HashMap<String, Integer> coursePreference = new HashMap<>(4);
		coursePreference.put("cs6035", 3);
		coursePreference.put("cs6210", 2);
		coursePreference.put("cs6238", 1);
		coursePreference.put("cs6241", 0);
		int studentId = 260510650;

		DbHelperForCore.putThisCoursePreferenceIntoDb(studentId, coursePreference);
		DbHelperForCore.AddCoursePreferenceIntoDbOnDemand(coursePreference);
		System.out.println(DbHelperForCore.loadOpenCourseListFromDb());
		System.out.println(DbHelperForCore.calcualteMaxCourseNumber(studentId, 2));
		System.out.println(DbHelperForCore.loadEntirePreferenceListFromDb().size());
		System.out.println(DbHelperForCore.loadEntirePreferenceListFromDb().get(260511621)[0]);
		System.out.println(DbHelperForCore.calcualteTotalPreferedStudentNumber());
		System.out.println(DbHelperForCore.makeCourseLabelNumberMapping());
		System.out.println(DbHelperForCore.convertToArrayList(DbHelperForCore.loadEntirePreferenceListFromDb()));
		System.out.println(DbHelperForCore.getEnumFinalPrefCourseList(
				DbHelperForCore.convertToArrayList(DbHelperForCore.loadEntirePreferenceListFromDb()),
				DbHelperForCore.makeCourseLabelNumberMapping()));
		System.out.println(DbHelperForCore.getAv());
	}
	

}

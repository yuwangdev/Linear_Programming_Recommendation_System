package codingTimeTest;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author yuwang
 *
 */

import Computing.Core;
import Computing.InputDataCache;

public class TestCore {

	@Test
	public void test() {
		HashMap<String, Integer> coursePreference = new HashMap<>(4);
		coursePreference.put("cs6210", 3);
		coursePreference.put("cs6250", 2);
		coursePreference.put("cs6505", 1);
		coursePreference.put("cse6740", 0);

		//coursePreference.put("cs6300", 3);

		Core core = new Core(false, 260510650, 3);

		core.loadThisStudentPreference(coursePreference);
		System.out.println(InputDataCache.coursePreference);
		System.out.println(InputDataCache.timeStamp);

		core.initialize();
		System.out.println(InputDataCache.openCourseList);
		System.out.println(InputDataCache.entirePreference);
		System.out.println(InputDataCache.maxCourseNum);
		System.out.println(InputDataCache.totalPreferedStudent);
		System.out.println(InputDataCache.courseNumberLableMapping);
		System.out.println(InputDataCache.semesterId);
		System.out.println(InputDataCache.totalNumSemester);
		System.out.println(InputDataCache.convertedPrefCourseList);
		System.out.println(InputDataCache.enumFinalPrefCourseList);

		core.postTreatData();
		System.out.println(core.getMark());
		System.out.println(core.getRawCourseCapacityByComputed());
		System.out.println(core.getFinalRecLists());

	}

}

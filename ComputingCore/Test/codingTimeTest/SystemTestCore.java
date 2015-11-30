package codingTimeTest;

import java.util.HashMap;
/**
 * @author yuwang
 *
 */

import Computing.Core;

public class SystemTestCore {

	public static void main(String[] args) throws Exception {
		HashMap<String, Integer> coursePreference = new HashMap<>(4);
		//coursePreference.put("cs6210", 3);
		coursePreference.put("cs6250", 2);
		//coursePreference.put("cs6505", 1);
		//coursePreference.put("cse6740", 0);

		// TODO Auto-generated method stub
		Core core = new Core(false, 260510650, 3);

		core.loadThisStudentPreference(coursePreference);
		core.initialize();

		try {
			core.solve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		core.postTreatData();
		System.out.println(core.getFinalRecLists());
		System.out.println(core.getMark());
		System.out.println(core.getRawCourseCapacityByComputed());
		

	}

}

package codingTimeTest;

import static org.junit.Assert.*;
import Computing.postTreatment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import Computing.OutputDataCache;

import org.junit.Test;

public class TestPostTreatment {

	@Test
	public void test() {
		String timeStamp = "'" + "Tue 2015.11.24 at 07:58:36 PM EST" + "'";

		int studentId = 260510659;
		int rawComputedCapacity = 170;

		HashMap<String, Integer> coursePreference = new HashMap<>(4);
		coursePreference.put("cs6210", 3);
		coursePreference.put("cs6250", 2);
		coursePreference.put("cs6505", 1);
		coursePreference.put("cse6740", 0);

		postTreatment pt = new postTreatment(timeStamp, studentId, rawComputedCapacity, coursePreference);
		System.out.println("Testing postTreatment");

		System.out.println(pt.getCurrentDemand("cs6210"));
		System.out.println(pt.getPreSeat("cs6290"));
		System.out.println(pt.getOneAvaiableElecCourseThatThisStudentNeverTaken(studentId, rawComputedCapacity));
		System.out.println(pt.getOneAvaiableMustCourseThatThisStudentNeverTaken(studentId, rawComputedCapacity));
		System.out.println(pt.getOneAvaiableFundaCourseThatThisStudentNeverTaken(studentId, rawComputedCapacity));
		System.out.println(pt.ifFunda(studentId));
		System.out.println(pt.ifMust(studentId));
		System.out.println(pt.ifElec(studentId));
		pt.doPostTreat();
		pt.putRecListIntoDb();
		pt.putRecSizeIntoDb();
		System.out.println(OutputDataCache.finalMark);
		System.out.println(OutputDataCache.finalRecLists);
		System.out.println(OutputDataCache.rawCourseCapacityByComputed);
		
		
	}

}

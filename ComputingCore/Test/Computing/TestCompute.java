package Computing;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class TestCompute {

	@Test
	public void test() {
		HashMap<String, Integer> coursePreference = new HashMap<>(4);
		coursePreference.put("cs6210", 3);
		coursePreference.put("cs6250", 2);
		coursePreference.put("cs6505", 1);
		coursePreference.put("cse6740", 0);

		// coursePreference.put("cs6300", 3);

		Core core = new Core(false, 260510650, 3);

		core.loadThisStudentPreference(coursePreference);
		core.initialize();
		try {
			core.solve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

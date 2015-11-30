package Servelet;

import static org.junit.Assert.*;

import org.junit.Test;

public class HistoryTest {

	@Test
	public void test() {
		String id = "260510650";
		History h = new History(id);
		h.dig();
		h.getRec();
	}

}

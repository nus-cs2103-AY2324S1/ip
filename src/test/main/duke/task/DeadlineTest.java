package duke.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
	@Test
	public void toStringTest() {
		Task t = new Deadline("description", false, "2015-07-08 1914");
		assertEquals(t.toString, "  [D][ ] description (by: Jul 08 2015 07:14pm)");
	}
}

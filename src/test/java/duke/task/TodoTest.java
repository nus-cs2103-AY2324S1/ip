package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
	@Test
	public void toStringTest() {
		Task t = new Todo("description", false);
		assertEquals(t.toString(), "  [T][ ] description");
	}

}

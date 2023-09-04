package duke.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

	@Test
	public void writeToFileTest() {
		ToDo t = new ToDo("hello");
		String expected = "T | 0 | hello";
		assertEquals(t.writeToFile(), expected);
	}
}

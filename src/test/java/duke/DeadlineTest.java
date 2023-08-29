package duke;

import duke.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
	Parser parser = new Parser(new TaskList(), new Storage("data/duke.txt"), new Ui());
	LocalDateTime deadlineDate = parser.formatInputDate("20/02/08 1300");
	@Test
	public void testStringConversion() {
		assertEquals("[D][ ] eat me (by: 20th of February 2008, 1:00PM)",
						new Deadline("eat me", deadlineDate).toString());
	}
}

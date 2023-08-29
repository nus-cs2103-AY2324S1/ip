package duke;

import duke.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
	Parser parser = new Parser(new TaskList(), new Storage("data/duke.txt"), new Ui());
	LocalDateTime fromDate = parser.formatInputDate("20/02/08 1300");
	LocalDateTime toDate = parser.formatInputDate("23/09/30 1200");
	@Test
	public void testStringConversion() {
		assertEquals("[E][ ] eat me (from: 20th of February 2008, 1:00PM to: 23rd of September 2030, 12:00PM)",
						new Event("eat me", fromDate, toDate).toString());
	}

	@Test
	public void testIfFromIsBeforeTo() {
		assertEquals(true, fromDate.isBefore(toDate));
	}

}

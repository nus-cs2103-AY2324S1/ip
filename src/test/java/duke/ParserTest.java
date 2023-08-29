package duke;

import duke.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
		Parser parser = new Parser(new TaskList(), new Storage("data/duke.txt"), new Ui());


	public String formatLocalDateTimeToString(LocalDateTime dateTime) {
		String dayOfMonth = dateTime.getDayOfMonth() + getDayOfMonthSuffix(dateTime.getDayOfMonth());
		String month = dateTime.getMonth().toString();
		month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();// Capitalize the month name
		String year = String.valueOf(dateTime.getYear());
		String hour = dateTime.format(DateTimeFormatter.ofPattern("h"));
		String minute = dateTime.format(DateTimeFormatter.ofPattern("mm"));
		String amPm = dateTime.format(DateTimeFormatter.ofPattern("a"));

		return String.format("%s of %s %s, %s:%s%s", dayOfMonth, month, year, hour, minute, amPm);
	}

	public String getDayOfMonthSuffix(final int n) {
		if (n >= 11 && n <= 13) {
			return "th";
		}
		switch (n % 10) {
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
		}
	}

	@Test
	public void testFormatInputDate() {
		LocalDateTime deadlineDate = parser.formatInputDate("20/02/08 1300");
		assertEquals("20th of February 2008, 1:00PM", formatLocalDateTimeToString(deadlineDate));
	}
}

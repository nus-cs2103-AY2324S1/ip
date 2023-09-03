package duke.parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
	@Test
	public void formatDateTest() {
		String expected = "2023-06-29T06:00:00";
		String[] input = {"630am", "29june"};
		String actual = Parser.formatDate(input);
		assertEquals(expected, actual);
	}


}

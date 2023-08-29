package duke.util;

import duke.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserTest {

    @Test
    public void transformDateTimeFormat_validDateTimeString_transformed() throws InvalidDateTimeException {
        String input = "2023-08-28 1430";
        String expectedOutput = "Aug 28 2023, 2:30pm";

        assertEquals(expectedOutput, DateParser.transformDateTimeFormat(input));
    }

    @Test
    public void transformDateTimeFormat_validDateString_transformed() throws InvalidDateTimeException {
        String input = "2023-08-28";
        String expectedOutput = "Aug 28 2023";

        assertEquals(expectedOutput, DateParser.transformDateTimeFormat(input));
    }

    @Test
    public void transformDateTimeFormat_invalidDateString_exceptionThrown() {
        String input = "2023/08/28";

        assertThrows(InvalidDateTimeException.class, () -> DateParser.transformDateTimeFormat(input));
    }

    @Test
    public void transformDateTimeFormat_invalidDateTimeString_exceptionThrown() {
        String input = "2023/08/28 14:30";

        assertThrows(InvalidDateTimeException.class, () -> DateParser.transformDateTimeFormat(input));
    }

    @Test
    public void transformDateTimeFormat_invalidString_exceptionThrown() {
        String input = "invalid date";

        assertThrows(InvalidDateTimeException.class, () -> DateParser.transformDateTimeFormat(input));
    }
}

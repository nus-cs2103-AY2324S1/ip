package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests the {@code Parser} class.
 */
public class ParserTest {
    /**
     * Tests whether the {@code parseDate} appropriately parses all allowed date time formats.
     */
    @Test
    public void parseDate_validFormat_success() {
        LocalDateTime dateTimeMidnight = LocalDateTime.of(2023, 9, 5, 0, 0);
        LocalDateTime dateTimeNoon = LocalDateTime.of(2023, 9, 5, 12, 0);

        assertEquals(dateTimeMidnight, Parser.parseDate("5/9/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("5/09/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("05/9/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("05/09/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-9-5"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-9-05"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-09-5"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-09-05"));

        assertEquals(dateTimeNoon, Parser.parseDate("5/9/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("5/09/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("05/9/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("05/09/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-9-5 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-9-05 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-09-5 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-09-05 1200"));
    }

    /**
     * Tests whether the {@code parseDate} appropriately throws exception for invalid formats.
     */
    @Test
    public void parseDate_invalidFormat_exceptionThrown() {
        String dateTimeString = "2023-09-05, 1200";
        LocalDateTime dateTimeNoon = LocalDateTime.of(2023, 9, 5, 12, 0);

        try {
            assertEquals(dateTimeNoon, Parser.parseDate(dateTimeString));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals(
                    String.format(
                            Messages.ERROR_PREFIX,
                            String.format(
                                    Messages.INVALID_DATE_TIME_FORMAT, dateTimeString
                            )
                    ),
                    e.getMessage()
            );
        }
    }
}
package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {


    @Test
    public void parseDate_exceptionThrown() {
        try {
            Parser.parseDate("idk");
        } catch (DateTimeParseException e) {
            assertEquals("Text 'idk' could not be parsed at index 0", e.getMessage());
        }
    }
}

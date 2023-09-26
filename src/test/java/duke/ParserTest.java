package duke;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {


    @Test
    public void parseDate_exceptionThrown_success() {
        try {
            Parser.parseDate("idk");
        } catch (DateTimeParseException e) {
            Assertions.assertEquals("Text 'idk' could not be parsed at index 0", e.getMessage());
        }
    }
}

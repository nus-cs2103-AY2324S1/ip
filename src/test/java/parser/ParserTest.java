package parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseInput_emptyInput_runsWithoutException() {
        try {
            Parser parser = new Parser();
            String[] expectedOutput = new String[]{ "", ""};
            String[] actualOutput = parser.parseInput("");
            assertEquals(true, Arrays.equals(expectedOutput, actualOutput));
        } catch (Exception e) {
            fail();
        }

    }
    @Test
    public void parseUserDateTime_correctFormat_success() {
        Parser parser = new Parser();
        LocalDateTime expectedOutput = LocalDateTime.of(2023, 8, 10, 17, 30);
        LocalDateTime actualOutput = parser.parseDateTime("10-08-2023 1730");
        assertEquals(true, expectedOutput.isEqual(actualOutput));
    }
    @Test
    public void parseUserDateTime_incorrectFormat_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class,
                () -> {
                    Parser parser = new Parser();
                    parser.parseDateTime("10/08/23 5.30pm");
                });
    }
}
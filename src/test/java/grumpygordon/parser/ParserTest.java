package grumpygordon.parser;

import grumpygordon.exceptions.GrumpyGordonDateTimeFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDateTime_validDateTime_success() throws GrumpyGordonDateTimeFormatException {
        assertEquals(LocalDateTime.of(2021, 9, 1, 19, 32), Parser.parseDateTime("2021-09-01 19:32"));

        assertEquals(LocalDateTime.of(1995, 2, 28, 0, 0), Parser.parseDateTime("1995-02-28 00:00"));
    }
    @Test
    public void parseDateTime_invalidDateTime_exceptionThrown() throws GrumpyGordonDateTimeFormatException {
        try {
            assertEquals(LocalDateTime.of(2021, 9, 1, 19, 32), Parser.parseDateTime("2021-09-01 19-32"));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid datetime.\n", e.getMessage());
        }

        try {
            assertEquals(LocalDateTime.of(2021, 2, 31, 19, 32), Parser.parseDateTime("2021-02-31 19-32"));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date 'FEBRUARY 31'", e.getMessage());
        }
    }


}

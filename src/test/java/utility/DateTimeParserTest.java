package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {

    @Test
    public void parse_corectFormat_success() {
        assertEquals(LocalDateTime.of(2023, 9, 1, 10, 30), DateTimeParser.parse("1-9-2023 1030"));
        assertEquals(LocalDateTime.of(2023, 9, 1, 10, 30), DateTimeParser.parse("1/9/2023 10:30"));
        assertEquals(LocalDateTime.of(2023, 9, 1, 10, 30), DateTimeParser.parse("01/09/2023 10:30"));
    }

    @Test
    public void parse_noTimeGiven_success() {
        assertEquals(LocalDateTime.of(2023, 9, 1, 00, 00), DateTimeParser.parse("1-9-2023"));
        assertEquals(LocalDateTime.of(2023, 9, 1, 00, 00), DateTimeParser.parse("1/9/2023"));
    }

    @Test
    public void parse_wrongFormat_null() {
        assertNull(DateTimeParser.parse("1/9-2023"));
        assertNull(DateTimeParser.parse("1.9.2023"));
        assertNull(DateTimeParser.parse("1/9/2023 2550"));
        assertNull(DateTimeParser.parse("1/9/2023 2:10"));
        assertNull(DateTimeParser.parse("1/19/2023 14:10"));
        assertNull(DateTimeParser.parse("35/9/2023 14:10"));
    }

}

package juke.parsers;

import juke.exceptions.JukeParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeParserTest {
    @Test
    public void isValidDate_normal_success() {
        assertTrue(DateTimeParser.isValidDate("15-02-2023"));
        assertTrue(DateTimeParser.isValidDate("15/02/2023"));
    }

    @Test
    public void isValidDate_invalidDate_success() {
        assertFalse(DateTimeParser.isValidDate("32-12-2023"));
        assertFalse(DateTimeParser.isValidDate("12-14-2023"));
        assertFalse(DateTimeParser.isValidDate("12-05-1000000"));
    }

    @Test
    public void isValidDate_whitespaced_failure() {
        assertFalse(DateTimeParser.isValidDate("  15-02-2023  "));
        assertFalse(DateTimeParser.isValidDate("  15/02/2023  "));
    }

    @Test
    public void isValidDate_excessivelyWhitespaced_failure() {
        assertFalse(DateTimeParser.isValidDate("  15   -   02   -   2023  "));
        assertFalse(DateTimeParser.isValidDate("  15   /   02   /   2023  "));
    }

    @Test
    public void isValidDate_illegalSymbols_failure() {
        assertFalse(DateTimeParser.isValidDate("15?02?2023"));
        assertFalse(DateTimeParser.isValidDate("15|02|2023"));
    }

    @Test
    public void isValidDate_gibberish_failure() {
        assertFalse(DateTimeParser.isValidDate("15002/2023"));
        assertFalse(DateTimeParser.isValidDate("15002|2023"));
        assertFalse(DateTimeParser.isValidDate("15002-2023"));

    }

    @Test
    public void isValidDateTime_normal_success() {
        assertTrue(DateTimeParser.isValidDateTime("15-02-2023 15-23"));
        assertTrue(DateTimeParser.isValidDateTime("15/02/2023 15-23"));
        assertTrue(DateTimeParser.isValidDateTime("15-02-2023 15:23"));
        assertTrue(DateTimeParser.isValidDateTime("15/02/2023 15:23"));
    }

    @Test
    public void isValidDateTime_whitespaced_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15-02-2023 15-23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15/02/2023 15-23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15-02-2023 15:23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15/02/2023 15:23   "));
    }

    @Test
    public void isValidDateTime_excessivelyWhitespaced_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15   -   02   -   2023   15   -   23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15   /   02   /   2023   15   -   23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15   -   02   -   2023   15   :   23   "));
        assertFalse(DateTimeParser.isValidDateTime("   15   /   02   /   2023   15   :   23   "));
    }

    @Test
    public void isValidDateTime_illegalSymbols_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15?02?2023 14?23"));
        assertFalse(DateTimeParser.isValidDateTime("15|02|2023 14|23"));
        assertFalse(DateTimeParser.isValidDateTime("15?02?2023 14|23"));
        assertFalse(DateTimeParser.isValidDateTime("15|02|2023 14?23"));
    }

    @Test
    public void isValidDateTime_gibberish_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15002/2023"));
        assertFalse(DateTimeParser.isValidDateTime("15002|2023"));
        assertFalse(DateTimeParser.isValidDateTime("15002-2023"));

    }

    @Test
    public void parse_normal_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15-02-2023").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15/02/2023").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15-02-2023").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15/02/2023").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15-02-2023 15-23").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15/02/2023 15-23").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15-02-2023 15:23").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15/02/2023 15:23").toString());
    }

    @Test
    public void parse_whitespace_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15-02-2023   ").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15/02/2023   ").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15-02-2023   ").toString());
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15/02/2023   ").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15-02-2023 15-23   ").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15/02/2023 15-23   ").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15-02-2023 15:23   ").toString());
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15/02/2023 15:23   ").toString());
    }

    @Test
    public void parse_excessiveWhitespaces_failure() {
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   -   02   -   2023   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   /   02   /   2023   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   -   02   -   2023   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   /   02   /   2023   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   -   02   -   2023   15   -   23   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   /   02   /   2023   15   -   23   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   -   02   -   2023   15   :   23   "));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("   15   /   02   /   2023   15   :   23   "));
    }

    @Test
    public void parse_gibberish_failure() {
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002/2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002|2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002-2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002/2023 14-23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002/2023 14:23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002|2023 14-23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002|2023 14:23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002-2023 14-23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15002-2023 14:23"));
    }

    @Test
    public void parse_illegalSymbols_failure() {
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15|02|2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15?02?2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15|02|2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15?02?2023"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15|02|2023 14|23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15?02?2023 14?23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15|02|2023 14?23"));
        assertThrows(JukeParseException.class, () -> DateTimeParser.parse("15?02?2023 14|23"));
    }
}

package shiba.datetimeformats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.InvalidCommandException;

public class DateOptionalTimeTest {
    @Test
    public void constructor_isoFormat_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-08-28T20:31");
        assertEquals("2023-08-28T20:31", dateOptionalTime.toString());
    }

    @Test
    public void constructor_isoFormatNoT_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-08-28 20:34");
        assertEquals("2023-08-28T20:34", dateOptionalTime.toString());
    }

    @Test
    public void constructor_isoDateOnly_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-08-10");
        assertEquals("2023-08-10", dateOptionalTime.toString());
    }

    @Test
    public void constructor_isoFormat_invalid1() {
        assertThrows(InvalidCommandException.class, () -> new DateOptionalTime("2023-28-08T20:31"));
    }

    @Test
    public void constructor_isoFormat_invalid2() {
        assertThrows(InvalidCommandException.class, () -> new DateOptionalTime("2023-08 28T20:31"));
    }

    @Test
    public void constructor_isoFormatNoT_invalid1() {
        assertThrows(InvalidCommandException.class, () -> new DateOptionalTime("2023-28-08 20:31"));
    }

    @Test
    public void constructor_isoFormatNoT_invalid2() {
        assertThrows(InvalidCommandException.class, () -> new DateOptionalTime("2023 08 28 20:31"));
    }

    @Test
    public void getDisplayRestr_isoFormat_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-08-28T20:31");
        assertEquals("28 Aug 2023 8:31PM", dateOptionalTime.getDisplayRepr());
    }

    @Test
    public void getDisplayRestr_isoFormatNoT_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-06-14 10:00");
        assertEquals("14 Jun 2023 10:00AM", dateOptionalTime.getDisplayRepr());
    }

    @Test
    public void getDisplayRestr_isoDateOnly_success() throws InvalidCommandException {
        DateOptionalTime dateOptionalTime = new DateOptionalTime("2023-02-01");
        assertEquals("1 Feb 2023", dateOptionalTime.getDisplayRepr());
    }
}

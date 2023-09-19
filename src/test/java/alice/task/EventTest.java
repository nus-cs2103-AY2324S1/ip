package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        try {
            Event event = new Event("birthday party", "2023-08-06T14:00:00", "2023-08-06T20:00:00");
            assertEquals(
                "[E][ ] birthday party [] (from: 2:00 PM, Aug 6 2023 to: 8:00 PM, Aug 6 2023)",
                event.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Event event = new Event("business meeting", "2023-11-16T08:45:00",
                "2023-11-16T10:00:00", true, "work", "urgent");
            assertEquals(
                "[E][X] business meeting [work, urgent] (from: 8:45 AM, Nov 16 2023 to: 10:00 AM, Nov 16 2023)",
                event.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void testFileStringConversion() {
        try {
            Event event = new Event("birthday party", "2023-08-06T14:00:00", "2023-08-06T20:00:00",
                false, "party");
            assertEquals("E | 0 | birthday party | [party] | 2023-08-06T14:00 | 2023-08-06T20:00",
                event.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Event event = new Event("business meeting", "2023-11-16T08:45:00",
                "2023-11-16T10:00:00", true, "work", "urgent");
            assertEquals("E | 1 | business meeting | [work, urgent] | 2023-11-16T08:45 | 2023-11-16T10:00",
                event.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        try {
            new Event("", "2023-08-06T14:00:00", "2023-08-06T20:00:00");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(Task.EMPTY_DESCRIPTION_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateTime_exceptionThrown() {
        try {
            new Event("birthday party", "no idea :)", "also no idea :)");
            fail(); // the test should not reach this line
        } catch (DateTimeParseException e) {
            // the test should reach this line
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }
}

package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import exceptions.InvalidTimeFormatException;
import helpers.Ui;

/**
 * Test class for testing Event object and operations
 */
public class EventsTest {
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Unit test to check if user is able to mark Event Task as done
     */
    @Test
    public void testEventMarkDoneSuccess() {
        Events event = new Events("internship talk", false,
                LocalDateTime.parse("2023-09-01 14:00", dateTimeFormat),
                LocalDateTime.parse("2023-09-01 16:00", dateTimeFormat));
        assertEquals(event.markedAsDone().toString(),
                "[E][x] internship talk (from: 2023-09-01 14:00 to: 2023-09-01 16:00)");
    }

    /**
     * Unit test to check if user is able to mark Event Task as not done
     */
    @Test
    public void testEventUnmarkDoneSuccess() {
        Events event = new Events("internship talk", false,
                LocalDateTime.parse("2023-09-01 14:00", dateTimeFormat),
                LocalDateTime.parse("2023-09-01 16:00", dateTimeFormat));
        assertEquals(event.markedAsUndone().toString(),
                "[E][ ] internship talk (from: 2023-09-01 14:00 to: 2023-09-01 16:00)");
    }


    /**
     * Unit test to check if program is able to throw a proper error on invalid date input
     */
    @Test
    public void testInvalidEventException() {
        Ui ui = new Ui();
        try {
            Events event = new Events("internship talk", false,
                    LocalDateTime.parse("invalid date", dateTimeFormat),
                    LocalDateTime.parse("invalid date", dateTimeFormat));
            fail();
        } catch (Exception e) {
            assertEquals("Macho! The input time format of a " + "invalid date"
                            + " is wrong, macho! Please enter in format "
                            + "yyyy-MM-dd HH:mm, macho!\n"
                            + "____________________________________________________________",
                    new InvalidTimeFormatException("invalid date", ui.getDivider()).getMessage());
        }


    }
}

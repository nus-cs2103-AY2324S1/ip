package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import exceptions.InvalidTimeFormatException;
import helpers.Ui;

/**
 * Test class for testing Deadline object and operations
 */
public class DeadlineTest {

    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Unit test to check if user is able to mark Deadline Task as done
     */
    @Test
    public void testDeadlineMarkDoneSuccess() {
        Deadline deadline = new Deadline("finish homework", false,
                LocalDateTime.parse("2023-09-01 23:59", dateTimeFormat));
        assertEquals(deadline.markedAsDone().toString(), "[D][x] finish homework (by: 2023-09-01 23:59)");
    }

    /**
     * Unit test to check if user is able to mark Deadline Task as not done
     */
    @Test
    public void testDeadlineUnmarkDoneSuccess() {
        Deadline deadline = new Deadline("finish homework", false,
                LocalDateTime.parse("2023-09-01 23:59", dateTimeFormat));
        assertEquals(deadline.markedAsUndone().toString(), "[D][ ] finish homework (by: 2023-09-01 23:59)");
    }


    /**
     * Unit test to check if program is able to throw a proper error on invalid date input
     */
    @Test
    public void testInvalidDeadlineException() {
        Ui ui = new Ui();
        try {
            Deadline deadline = new Deadline("finish homework", false,
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

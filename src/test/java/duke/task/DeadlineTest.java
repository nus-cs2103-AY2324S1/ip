package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        try {
            Deadline deadline = new Deadline("return book", "2023-08-06T14:00:00");
            assertEquals("[D][ ] return book (by: 2:00 PM, Aug 6 2023)", deadline.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Deadline deadline = new Deadline("study", "2023-11-16T08:45:00", true);
            assertEquals("[D][X] study (by: 8:45 AM, Nov 16 2023)", deadline.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void testFileStringConversion() {
        try {
            Deadline deadline = new Deadline("return book", "2023-08-06T14:00:00");
            assertEquals("D | 0 | return book | 2023-08-06T14:00", deadline.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Deadline deadline = new Deadline("study", "2023-11-16T08:45:00", true);
            assertEquals("D | 1 | study | 2023-11-16T08:45", deadline.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        try {
            new Deadline("", "2023-08-06T14:00:00");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateTime_exceptionThrown() {
        try {
            new Deadline("return book", "no idea :)");
            fail(); // the test should not reach this line
        } catch (DateTimeParseException e) {
            // the test should reach this line
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }
}

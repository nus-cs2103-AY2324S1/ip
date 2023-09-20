package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void deadline_addDeadline_success() {
        assertEquals("[D][ ] deadline (by: Oct 12 2024)", new Deadline("deadline", "2024-10-12").toString());
    }

    @Test
    public void deadline_invalidDate_exceptionThrown() {
        try {
            assertEquals("deadline added", new Deadline("deadline", "2024-02-40"));
            fail();
        } catch (Exception e) {
            assertEquals("Text '2024-02-40' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 40", e.getMessage());
        }
    }

    @Test
    public void deadline_missingDate_exceptionThrown() {
        try {
            assertEquals("deadline added", new Deadline("deadline", ""));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("date format is invalid", e.getMessage());
        }
    }

    @Test
    public void deadline_earlierDate_exceptionThrown() {
        try {
            assertEquals("deadline deadline (by: Oct 12 2020)", new Deadline("deadline", "2020-10-12"));
            fail();
        } catch (Exception e) {
            assertEquals("date is before today", e.getMessage());
        }
    }

}

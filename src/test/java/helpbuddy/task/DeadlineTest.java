package helpbuddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import helpbuddy.exception.HelpBuddyException;
public class DeadlineTest {

    @Test
    public void deadline_noTaskName_exceptionThrown() {
        try {
            new Deadline("", null);
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! The description of a deadline cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void deadline_noDeadline_exceptionThrown() {
        try {
            new Deadline("read", null);
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! Please enter /by followed by a deadline.\n", e.getMessage());
        }
    }

    @Test
    public void deadline_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        try {
            Deadline task = new Deadline("read", LocalDateTime.parse("31/08/23 17:41", formatter));
            assertEquals("D|0|read|2023-08-31T17:41", task.stringifyTask());
        } catch (HelpBuddyException e) {
            fail();
        }
    }
}

package helpbuddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import helpbuddy.exception.HelpBuddyException;

public class EventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    @Test
    public void event_noTaskName_exceptionThrown() {
        try {
            new Event("", null, null);
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! The description of a event cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void event_noStartTime_exceptionThrown() {
        try {
            new Event("meeting", null, null);
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! Please enter /from followed by a start time of event.\n", e.getMessage());
        }
    }

    @Test
    public void event_noEndTime_exceptionThrown() {
        try {
            new Event("meeting", LocalDateTime.parse("31/08/23 17:41", formatter), null);
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! Please enter /to followed by an end time of event.\n", e.getMessage());
        }
    }

    @Test
    public void event_startEqualsEnd_exceptionThrown() {
        try {
            new Event(
                    "meeting",
                    LocalDateTime.parse("31/08/23 17:41", formatter),
                    LocalDateTime.parse("31/08/23 17:41", formatter)
            );
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! Both start and end time are the same, please check!\n", e.getMessage());
        }
    }

    @Test
    public void event_endBeforeStart_exceptionThrown() {
        try {
            new Event(
                    "meeting",
                    LocalDateTime.parse("31/08/23 17:41", formatter),
                    LocalDateTime.parse("31/08/23 17:40", formatter)
            );
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! End time must be after the start time!\n", e.getMessage());
        }
    }

    @Test
    public void event_success() {
        try {
            Event task = new Event(
                    "meeting",
                    LocalDateTime.parse("31/08/23 17:41", formatter),
                    LocalDateTime.parse("31/08/23 18:41", formatter)
            );
            assertEquals("E|0|meeting|2023-08-31T17:41 to 2023-08-31T18:41", task.stringifyTask());
        } catch (HelpBuddyException e) {
            fail();
        }
    }
}

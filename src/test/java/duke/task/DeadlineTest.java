package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    @Test
    public void testCreate() {
        Deadline d = new Deadline("Message",
                LocalDateTime.parse("2023-10-10T12:00:00"));
        assertEquals("Message", d.message);
    }

    @Test
    public void testToSaveFormatString() {
        Deadline d = new Deadline("Message",
                LocalDateTime.parse("2023-10-10T12:00:00"));
        assertEquals("D | 0 | Message | 2023-10-10T12:00", d.toSaveFormatString());
        d.markAsDone();
        assertEquals("D | 1 | Message | 2023-10-10T12:00", d.toSaveFormatString());
    }

    @Test
    public void testToString() {
        Deadline d = new Deadline("Message",
                LocalDateTime.parse("2023-12-13T12:45:00"));
        assertEquals("[D][ ] Message (by: Dec 13 2023, 12:45:00)", d.toString());
        d.markAsDone();
        assertEquals("[D][X] Message (by: Dec 13 2023, 12:45:00)", d.toString());
    }

    @Test
    public void testUpdate_validDate_success() {
        try {
            Deadline d = new Deadline("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"));
            d.update(UpdateType.DESCRIPTION, "New Deadline");
            d.update(UpdateType.DATE1, "2023-10-09T12:34:56");
            assertEquals("[D][ ] New Deadline (by: Oct 09 2023, 12:34:56)",
                    d.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdate_invalidDate_dukeExceptionThrown() {
        try {
            Deadline d = new Deadline("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"));
            d.update(UpdateType.DESCRIPTION, "New Deadline");
            d.update(UpdateType.DATE1, "this is not a date");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot parse date/time of new deadline!", e.getMessage());
        }
    }

    @Test
    public void testUpdate_invalidUpdateDateType_dukeExceptionThrown() {
        try {
            Deadline d = new Deadline("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"));
            d.update(UpdateType.DESCRIPTION, "New Deadline");
            d.update(UpdateType.DATE2, "2023-10-10T12:00:01");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot update: Deadlines have only one deadline date!", e.getMessage());
        }
    }

    @Test
    public void testClone() {
        try {
            Deadline d = new Deadline("Deadline",
                    LocalDateTime.parse("2023-10-10T12:00:00"));
            d.markAsDone();
            d.update(UpdateType.DESCRIPTION, "New Deadline");
            Deadline d2 = d.clone();
            d.update(UpdateType.DESCRIPTION, "Old Deadline");
            d2.update(UpdateType.DATE1, "2023-10-09T12:34:56");
            assertEquals("[D][X] Old Deadline (by: Oct 10 2023, 12:00:00)",
                    d.toString());
            assertEquals("[D][ ] New Deadline (by: Oct 09 2023, 12:34:56)",
                    d2.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }
}

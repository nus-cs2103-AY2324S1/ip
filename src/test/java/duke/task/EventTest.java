package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {

    @Test
    public void testCreate_validDates_success() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            assertEquals("Message", ev.message);
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testCreate_equalDates_dukeExceptionThrown() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-10T12:00:00"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date parameter: From date must be before to date!", e.getMessage());
        }
    }

    @Test
    public void testCreate_invalidDates_dukeExceptionThrown() {
        try {
            Event ev = new Event("Error",
                    LocalDateTime.parse("2023-10-11T12:00:00"),
                    LocalDateTime.parse("2023-10-10T13:30:45"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date parameter: From date must be before to date!", e.getMessage());
        }
    }

    @Test
    public void testToSaveFormatString() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:30:45"),
                    LocalDateTime.parse("2023-10-11T13:01:00"));
            assertEquals("E | 0 | Message | 2023-10-10T12:30:45 | 2023-10-11T13:01",
                    ev.toSaveFormatString());
            ev.markAsDone();
            assertEquals("E | 1 | Message | 2023-10-10T12:30:45 | 2023-10-11T13:01",
                    ev.toSaveFormatString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testToString() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:15:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            assertEquals("[E][ ] Message (from: Oct 10 2023, 12:15:00 to: Oct 11 2023, 13:30:45)",
                    ev.toString());
            ev.markAsDone();
            assertEquals("[E][X] Message (from: Oct 10 2023, 12:15:00 to: Oct 11 2023, 13:30:45)",
                    ev.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdate_validDates_success() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.update(UpdateType.DESCRIPTION, "New Event");
            ev.update(UpdateType.DATE1, "2023-10-09T12:34:56");
            ev.update(UpdateType.DATE2, "2023-10-10T11:34:56");
            assertEquals("[E][ ] New Event (from: Oct 09 2023, 12:34:56 to: Oct 10 2023, 11:34:56)",
                    ev.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdate_invalidDate1_dukeExceptionThrown() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.update(UpdateType.DESCRIPTION, "New Event");
            ev.update(UpdateType.DATE1, "this is not a date");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot parse date/time of new event start date!", e.getMessage());
        }
    }

    @Test
    public void testUpdate_invalidDate2_dukeExceptionThrown() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.update(UpdateType.DESCRIPTION, "New Event");
            ev.update(UpdateType.DATE2, "this is not a date");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot parse date/time of new event end date!", e.getMessage());
        }
    }


    @Test
    public void testUpdate_badOrder_dukeExceptionThrown() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.update(UpdateType.DESCRIPTION, "New Event");
            ev.update(UpdateType.DATE2, "2023-10-10T11:34:56");
            ev.update(UpdateType.DATE1, "2023-10-09T12:34:56");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Invalid date parameter: From date must be before to date!", e.getMessage());
        }
    }

    @Test
    public void testUpdate_equalDates_dukeExceptionThrown() {
        try {
            Event ev = new Event("Message",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.update(UpdateType.DESCRIPTION, "New Event");
            ev.update(UpdateType.DATE2, "2023-10-10T12:00:00");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Invalid date parameter: From date must be before to date!", e.getMessage());
        }
    }

    @Test
    public void testClone() {
        try {
            Event ev = new Event("Event",
                    LocalDateTime.parse("2023-10-10T12:00:00"),
                    LocalDateTime.parse("2023-10-11T13:30:45"));
            ev.markAsDone();
            ev.update(UpdateType.DATE2, "2023-10-10T13:34:56");
            Event ev2 = ev.clone();
            ev2.update(UpdateType.DESCRIPTION, "New Event");
            ev2.update(UpdateType.DATE1, "2023-10-09T12:34:56");
            assertEquals("[E][X] Event (from: Oct 10 2023, 12:00:00 to: Oct 10 2023, 13:34:56)",
                    ev.toString());
            assertEquals("[E][ ] New Event (from: Oct 09 2023, 12:34:56 to: Oct 10 2023, 13:34:56)",
                    ev2.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }
}

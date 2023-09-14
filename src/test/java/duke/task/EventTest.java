package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testCreate() {
        Event e = new Event("Message",
                LocalDateTime.parse("2023-10-10T12:00:00"),
                LocalDateTime.parse("2023-10-11T13:30:45"));
        assertEquals("Message", e.message);
    }

    @Test
    public void testToSaveFormatString() {
        Event e = new Event("Message",
                LocalDateTime.parse("2023-10-10T12:30:45"),
                LocalDateTime.parse("2023-10-11T13:01:00"));
        assertEquals("E | 0 | Message | 2023-10-10T12:30:45 | 2023-10-11T13:01", e.toSaveFormatString());
        e.markAsDone();
        assertEquals("E | 1 | Message | 2023-10-10T12:30:45 | 2023-10-11T13:01", e.toSaveFormatString());
    }

    @Test
    public void testToString() {
        Event e = new Event("Message",
                LocalDateTime.parse("2023-10-10T12:15:00"),
                LocalDateTime.parse("2023-10-11T13:30:45"));
        assertEquals("[E][ ] Message (from: Oct 10 2023, 12:15:00 to: Oct 11 2023, 13:30:45)", e.toString());
        e.markAsDone();
        assertEquals("[E][X] Message (from: Oct 10 2023, 12:15:00 to: Oct 11 2023, 13:30:45)", e.toString());
    }
}

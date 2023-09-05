package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
}

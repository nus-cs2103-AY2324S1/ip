package duke;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void testToString() {
        Event newEvent = new Event("group meeting", formatDateTime("2/9/2023 1000"), formatDateTime("2/9/2023 1130"));
        assertEquals("[E][ ] group meeting (from: Sep 02 2023 10:00, to: Sep 02 2023 11:30)", newEvent.toString());

        Event done = new Event("attend tutorial", formatDateTime("1/9/2023 1000"), formatDateTime("1/9/2023 1130"));
        done.markAsDone();
        assertEquals("[E][X] attend tutorial (from: Sep 01 2023 10:00, to: Sep 01 2023 11:30)", done.toString());
    }

    @Test
    public void testAsMark() {
        Event event = new Event("discussion", formatDateTime("10/9/2023 1000"), formatDateTime("10/9/2023 1130"));
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
    }

    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}

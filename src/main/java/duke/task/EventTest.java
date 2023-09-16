package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void stringifyTaskTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime from = LocalDateTime.parse("01/10/20 00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("20/12/23 20:00", formatter);
        Event event = new Event("test", from, to);
        assertEquals("E|0|test|Oct 1 2020 00:00|Dec 20 2023 20:00", event.stringifyTask(),
                "'toLogString()' should return a simplified string to be saved to file");
    }

    @Test
    public void toStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime from = LocalDateTime.parse("01/10/20 00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("20/12/23 20:00", formatter);
        Event event = new Event("test", from, to);
        assertEquals("[E][ ] test (from: Oct 1 2020 00:00 to: Dec 20 2023 20:00)", event.toString(),
                "'toString()' should return a string to be printed");
    }
}


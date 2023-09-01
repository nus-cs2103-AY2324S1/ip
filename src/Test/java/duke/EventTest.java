package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    public void check_event_contentLine() {

        Event e = new Event("Checking", false, LocalDateTime.of(2019, 12, 2, 19, 00),
                LocalDateTime.of(2023, 8, 31, 2, 20));
        assertEquals("E/ /Checking/2019-12-02T19:00/2023-08-31T02:20", e.contentLine());
    }

    @Test
    public void check_event_toString() {

        Event e = new Event("Checking", true, LocalDateTime.of(2019, 12, 2, 19, 00),
                LocalDateTime.of(2023, 8, 31, 2, 20));
        assertEquals("[E][X] Checking (from: 02/Dec/2019 19:00 to: 31/Aug/2023 02:20)", e.toString());
    }
}

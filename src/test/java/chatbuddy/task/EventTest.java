package chatbuddy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    // create event object
    LocalDateTime from = LocalDateTime.parse("01/09/2023 1000", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    LocalDateTime to = LocalDateTime.parse("01/09/2023 1732", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    Event event = new Event("event1", from, to);

    @Test
    public void getInformationForSaving() {
        assertEquals("E | 0 | event1 | 01/09/2023 1000 | 01/09/2023 1732", event.getInformationForSaving());
    }
}

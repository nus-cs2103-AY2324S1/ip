import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void eventStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ha");
        LocalDateTime startDate = LocalDateTime.parse("2023-08-31 2PM", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2023-09-01 1PM", formatter);
        String taskName = "project meeting";
        assertEquals("[E] [ ] project meeting (from: Aug 31 2023 2PM to: Sep 1 2023 1PM)",
                new Event(taskName, startDate, endDate).toString());
    }
}

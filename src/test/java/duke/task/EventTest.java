package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toString_correctFormat() {
        Event event = new Event("Meeting", "2023-09-19 1400", "2023-09-19 1500");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        LocalDateTime from = LocalDateTime.parse("2023-09-19 1400", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse("2023-09-19 1500", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String expectedOutput = "[E][ ] Meeting (from: " + from.format(formatter) + " to: " + to.format(formatter)
                + ")";
        assertEquals(expectedOutput, event.toString());
    }

    @Test
    public void toFileString_correctFormat() {
        Event event = new Event("Meeting", "2023-09-19 1400", "2023-09-19 1500");
        String expectedOutput = "E | 0 | Meeting | 2023-09-19 1400 to 2023-09-19 1500";
        assertEquals(expectedOutput, event.toFileString());
    }
}

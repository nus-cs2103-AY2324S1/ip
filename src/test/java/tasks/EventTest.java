package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void eventTest1() {
        LocalDateTime startTime = LocalDateTime.parse("2023-09-12 14:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endTime = LocalDateTime.parse("2023-09-12 16:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try {
            Event event1 = new Event("cs2103 assignment", startTime, endTime);
            assertEquals(event1.getTaskAsString(), "[E][ ] cs2103 assignment (from: Sep 12 2023 14:00 "
                   + "to: Sep 12 2023 16:00)");
        } catch (Exception e) {
            fail();
        }
    }
}

package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        LocalDateTime fromDateTime = LocalDateTime.parse("2023-09-23 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime toDateTime = LocalDateTime.parse("2023-09-23 2000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("[E][ ] Apply for internships (from: Sep 23 2023 1800 to: Sep 23 2023 2000)",
                new Event("Apply for internships", fromDateTime, toDateTime).toString());

        Task task = new Event("Make my resume", fromDateTime, toDateTime);
        task.setMark(true);

        assertEquals("[E][X] Make my resume (from: Sep 23 2023 1800 to: Sep 23 2023 2000)", task.toString());
    }

    @Test
    public void testWriteToFile() {
        LocalDateTime fromDateTime = LocalDateTime.parse("2023-09-23 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime toDateTime = LocalDateTime.parse("2023-09-23 2000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("E | 0 | Apply for internships | Sep 23 2023 1800 -> Sep 23 2023 2000",
                new Event("Apply for internships", fromDateTime, toDateTime).writeToFile());

        Task task = new Event("Make my resume", fromDateTime, toDateTime);
        task.setMark(true);

        assertEquals("E | 1 | Make my resume | Sep 23 2023 1800 -> Sep 23 2023 2000", task.writeToFile());
    }
}

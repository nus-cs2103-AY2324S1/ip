package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-09-23 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("[D][ ] Apply for internships (by: Sep 23 2023 1800)", new Deadline("Apply for internships", dateTime).toString());

        Task task = new Deadline("Make my resume", dateTime);
        task.setMark(true);

        assertEquals("[D][X] Make my resume (by: Sep 23 2023 1800)", task.toString());
    }

    @Test
    public void testWriteToFile() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-09-23 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("D | 0 | Apply for internships | Sep 23 2023 1800", new Deadline("Apply for internships", dateTime).writeToFile());

        Task task = new Deadline("Make my resume", dateTime);
        task.setMark(true);

        assertEquals("D | 1 | Make my resume | Sep 23 2023 1800", task.writeToFile());
    }
}

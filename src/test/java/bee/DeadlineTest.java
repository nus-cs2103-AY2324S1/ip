package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void initializeTest1() {
        String deadlineDateString = "2023-08-31 2359";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, formatter);
        Deadline deadline = new Deadline("Deadline Test", deadlineDate);
        assertEquals("[D][ ] Deadline Test (by: Aug 31 2023 23:59)", deadline.toString());
    }
    @Test
    public void initializeTest2() {
        String deadlineDateString = "2023-08-31 2359";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, formatter);
        boolean isDone = true;
        Deadline deadline = new Deadline("Deadline Test", deadlineDate, isDone);
        assertEquals("[D][X] Deadline Test (by: Aug 31 2023 23:59)", deadline.toString());
    }
}

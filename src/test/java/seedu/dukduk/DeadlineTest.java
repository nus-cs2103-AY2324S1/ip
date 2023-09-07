package seedu.dukduk;

import org.junit.jupiter.api.Test;
import dukduk.Deadline;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 15, 14, 30);
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        String expected = "[D][ ] Test Deadline (by: Sep 15 2023 14:30)";
        String actual = deadline.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToDataString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 15, 14, 30);
        Deadline deadline = new Deadline("Test Deadline", dateTime);
        deadline.markAsDone();

        String expected = "D | 1 | Test Deadline | 15-09-2023 14:30";
        String actual = deadline.toDataString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToDataStringUnmarked() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 15, 14, 30);
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        String expected = "D | 0 | Test Deadline | 15-09-2023 14:30";
        String actual = deadline.toDataString();

        assertEquals(expected, actual);
    }
}

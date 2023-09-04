package duke.task;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testConvertDateTimeValid() {
        Deadline deadline = new Deadline("homework", "12/09/2080 1718");
        LocalDateTime actual = deadline.convertDateTime("12/09/2080 1718");
        LocalDateTime expected = LocalDateTime.of(2080, 9, 12, 17, 18);
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertDateTimeInvalid() {
        Deadline deadline = new Deadline("homework", "12/09/2080 1718");
        assertThrows(IllegalArgumentException.class, () -> {
            deadline.convertDateTime("hello bye!");
        });
    }

    @Test
    public void testFormatDateTime() {
        Deadline deadline = new Deadline("homework", "12/09/2080 1718");
        LocalDateTime dateTime = LocalDateTime.of(2080, 9, 12, 17, 18);
        String actual = deadline.formatDateTime(dateTime);
        String expected = "12 Sep 2080 5:18 PM"; // Adjust the expected value accordingly
        assertEquals(expected, actual);
    }

    @Test
    public void testDeadlineString() {
        Task task = new Deadline("math homework", "12/12/2002 1700");
        String expected = "[D][ ] math homework (by: 12 Dec 2002 5:00 PM)";

        assertEquals(expected, task.toString());
    }
}

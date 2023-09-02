package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("deadline description",
                LocalDate.EPOCH, LocalTime.NOON);
        String dString = deadline.toString();
        assertEquals("[D][ ] deadline description (by: 1970-01-01 12:00)",
                dString);
    }
}

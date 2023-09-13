package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("deadline description",
                "1970-01-01 12:00");
        String dString = deadline.toString();
        assertEquals("[D][ ] deadline description (by: 1970-01-01 12:00)",
                dString);
    }
}

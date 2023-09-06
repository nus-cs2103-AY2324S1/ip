package dialogix.main;

import task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    void testToString() {
        LocalDate byDate = LocalDate.of(2023, 9, 15);
        Deadline deadline = new Deadline("Finish project", byDate, null, false);
        assertEquals("[D][ ] Finish project (by: Sep 15 2023)", deadline.toString());
    }

    // Add more tests for other methods in Deadline class
}

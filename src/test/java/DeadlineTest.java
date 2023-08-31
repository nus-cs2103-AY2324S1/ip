package dialogix.main;

import dialogix.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDate byDate = LocalDate.of(2023, 9, 15);
        Deadline deadline = new Deadline("Finish project", byDate);
        assertEquals("[D][ ] Finish project (by: Sep 15 2023)", deadline.toString());
    }

    // Add more tests for other methods in Deadline class
}

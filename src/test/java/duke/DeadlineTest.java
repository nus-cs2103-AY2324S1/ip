package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        String expected = "[D][ ] Complete assignment (by: Sep 30 2023)";
        assertEquals(expected, task.toString());
    }

    @Test
    void testMark() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        task.mark();
        assertTrue(task.isMarked());
    }

    @Test
    void testUnmark() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        task.mark();
        assertTrue(task.isMarked());
        task.unMark();
        assertFalse(task.isMarked());
    }
}

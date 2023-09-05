package dook.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void isAfter_validInput_correctResult() {
        Deadline deadline = new Deadline("AAA", "2001-02-02", true);
        assertTrue(deadline.isAfter(LocalDate.of(2001, 1, 1)));
        assertFalse(deadline.isAfter(LocalDate.of(2001, 3, 3)));
    }
    @Test
    public void isBefore_validInput_correctResult() {
        Deadline deadline = new Deadline("AAA", "2001-02-02", true);
        assertFalse(deadline.isBefore(LocalDate.of(2001, 1, 1)));
        assertTrue(deadline.isBefore(LocalDate.of(2001, 3, 3)));
    }
    @Test
    public void isDuring_validInput_correctResult() {
        Deadline deadline = new Deadline("AAA", "2001-02-02", true);
        assertFalse(deadline.isDuring(LocalDate.of(2001, 1, 1)));
        assertTrue(deadline.isDuring(LocalDate.of(2001, 2, 2)));
    }

}

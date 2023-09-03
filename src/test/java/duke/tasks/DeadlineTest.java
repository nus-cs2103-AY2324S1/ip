package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private Deadline deadline1;
    private Deadline deadline2;

    @BeforeEach
    public void setUp() {
        deadline1 = new Deadline("Submit Assignment", LocalDateTime.of(2023, 9, 5, 16, 30));
        deadline2 = new Deadline("Complete Project", true, LocalDateTime.of(2023, 9, 10, 12, 0));
    }

    @Test
    public void toString_validDeadlines_correctOutput() {
        assertEquals("[D][ ] Submit Assignment (by: 05 Sep 2023, 16:30)", deadline1.toString());
        assertEquals("[D][X] Complete Project (by: 10 Sep 2023, 12:00)", deadline2.toString());
    }

    @Test
    public void constructor_differentDoneValues_correctOutput() {
        assertFalse(deadline1.isDone());
        assertTrue(deadline2.isDone());
    }
}
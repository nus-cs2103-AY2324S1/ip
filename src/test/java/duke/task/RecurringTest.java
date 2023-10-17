package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecurringTest {
    @Test
    public void testRecurringStringConversion() {
        assertEquals(
                "[R][ ] Lecture Quizzes (every: week)",
                new Recurring("Lecture Quizzes", "week").toString()
        );
    }
}

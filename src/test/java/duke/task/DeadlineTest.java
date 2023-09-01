package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadline = new Deadline("task", "11/11/2002 11:11", false);
    @Test
    public void testMarkDeadline() {
        assertEquals(true, deadline.markTask(), "Marking deadline should work");
    }

    @Test
    public void testUnmarkTodo() {
        assertEquals(false, deadline.markTask(), "Unmarking deadline should work");
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] task (by: 11/11/2002 11:00)", deadline.toString(), "deadline.toString() should work");
    }
}

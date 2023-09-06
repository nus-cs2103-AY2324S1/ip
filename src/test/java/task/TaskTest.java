package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Task("task description");
        assertEquals("[ ] task description", task.toString());
    }

    @Test
    public void testMarkDoneUndone() {
        Task task = new Task("task description");
        task.markDone();
        assertEquals("[X] task description", task.toString());

        task.markUndone();
        assertEquals("[ ] task description", task.toString());
    }
}

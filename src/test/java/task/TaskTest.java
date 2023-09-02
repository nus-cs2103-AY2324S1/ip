package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

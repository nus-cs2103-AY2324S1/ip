package duke.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testMarkDone() {
        Task testTask = new Task("testTask");
        testTask.markDone();
        assertTrue(testTask.getIsDone());
    }

    @Test
    public void testMarkUndone() {
        Task testTask = new Task("testTask");
        testTask.markDone();
        testTask.markUndone();
        assertFalse(testTask.getIsDone());
    }
}

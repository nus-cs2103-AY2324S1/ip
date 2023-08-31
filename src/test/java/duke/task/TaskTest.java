package duke.task;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    Task testTask = new Task("Task Description");

    @Test
    void testGetDescription() {
        assertEquals(testTask.getDescription(), "Task Description");
    }

    @Test
    void testToString() {
        Task task = new Task("To test description");
        assertEquals(task.toString(), "[ ] To test description");
    }

    @Test
    void testGetStatusIcon() {
        assertEquals(testTask.getStatusIcon(), " ");
        testTask.markAsDone();
        assertEquals(testTask.getStatusIcon(), "X");
        testTask.markAsNotDone();
        assertEquals(testTask.getStatusIcon(), " ");
    }

    @Test
    void markAsDone_success() {
        Task task = new Task("Second Task Description");
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }
}

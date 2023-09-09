package dre.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testInitialTaskStatus() {
        Task task = new Task("Sample");
        assertFalse(task.isDone(), "New task should not be marked as done.");
    }

    @Test
    public void testMarkTaskAsDone() {
        Task task = new Task("Sample");
        task.done();
        assertTrue(task.isDone(), "Task should be marked as done after calling markAsDone().");
    }

    @Test
    public void testFileSaveFormat() {
        Task task = new Task("something something");
        assertEquals(task.fileSaveFormat(), " |something something");
    }
}
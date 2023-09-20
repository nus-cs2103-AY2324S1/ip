package jo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jo.task.Task;

public class TaskTest {

    @Test
    public void getStatusIcon_notDone() {
        Task task = new Task("Sample task", false);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_done() {
        Task task = new Task("Sample task", true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void toString_notDone() {
        Task task = new Task("Sample task", false);
        assertEquals("[T][ ] Sample task", task.toString());
    }

    @Test
    public void toString_done() {
        Task task = new Task("Sample task", true);
        assertEquals("[T][X] Sample task", task.toString());
    }

    @Test
    public void markAsDone() {
        Task task = new Task("Sample task", false);
        task.mark(true);
        assertTrue(task.getIsDone());
    }

    @Test
    public void markAsNotDone() {
        Task task = new Task("Sample task", true);
        task.mark(false);
        assertFalse(task.getIsDone());
    }

    @Test
    public void toFile_notDone() {
        Task task = new Task("Sample task", false);
        assertEquals("T | 0 | Sample task", task.toFile());
    }

    @Test
    public void toFile_done() {
        Task task = new Task("Sample task", true);
        assertEquals("T | 1 | Sample task", task.toFile());
    }

    @Test
    public void getDescription() {
        Task task = new Task("Sample task", false);
        assertEquals("Sample task", task.getDescription());
    }
}

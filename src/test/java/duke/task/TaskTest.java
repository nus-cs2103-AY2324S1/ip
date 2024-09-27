package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    /**
     * Tests the markAsDone method to ensure it sets the task as done.
     */
    @Test
    public void testMarkAsDone() {
        Task task = new Task("Buy groceries");
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }

    /**
     * Tests the markAsUndone method to ensure it sets the task as not done.
     */
    @Test
    public void testMarkAsUndone() {
        Task task = new Task("Buy groceries", true);
        assertTrue(task.isDone);
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    /**
     * Tests the getStatusIcon method to ensure it returns "X" when the task is done
     * and returns " " (space) when the task is not done.
     */
    @Test
    public void testGetStatusIcon() {
        Task task = new Task("Buy groceries");
        assertEquals(" ", task.getStatusIcon());

        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Tests the toString method to ensure it returns a formatted string including the task's
     * completion status icon and description.
     */
    @Test
    public void testToString() {
        Task task = new Task("Buy groceries");
        assertEquals("[ ] Buy groceries", task.toString());

        task.markAsDone();
        assertEquals("[X] Buy groceries", task.toString());
    }
}

package pippi.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Sample task");
    }

    @Test
    public void testConstructor() {
        assertEquals("Sample task", task.getDescription());
        assertFalse(task.isDone);
    }

    @Test
    public void testMarkAsDone() {
        task.mark();
        assertTrue(task.isDone);
    }

    @Test
    public void testUnmark() {
        task.mark();
        task.unmark();
        assertFalse(task.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("[ ] ", task.getStatusIcon());
        task.mark();
        assertEquals("[X] ", task.getStatusIcon());
    }

    @Test
    public void testGetStatus() {
        assertEquals("| 0 | ", task.getStatus());
        task.mark();
        assertEquals("| 1 | ", task.getStatus());
    }

    @Test
    public void testToString() {
        assertEquals("[ ] Sample task", task.toString());
        task.mark();
        assertEquals("[X] Sample task", task.toString());
    }

    @Test
    public void testToMemory() {
        assertEquals("[ ] Sample task", task.toMemory());
    }
}
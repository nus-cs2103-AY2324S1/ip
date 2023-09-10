package jarvis.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Sample Task", false);
    }

    @Test
    public void getTitle_sampleTitle_success() {
        assertEquals("Sample Task", task.getTitle());
    }

    @Test
    public void markCompleted_markTestComplete_success() {
        task.markCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    public void unmarkCompleted_unmarkTestComplete_success() {
        task.unmarkCompleted();
        assertFalse(task.isCompleted());
    }

    @Test
    public void testSetAndGetDueDate() {
        LocalDateTime dueDate = LocalDateTime.of(2023, 9, 30, 12, 0);
        task.setDueDate(dueDate);
        assertEquals(dueDate, task.getDueDate());
    }
}
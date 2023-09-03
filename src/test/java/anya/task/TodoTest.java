package anya.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo t = new Todo("Test task");
    @Test
    public void markAsDoneTest() {
        t.markAsDone();
        assertTrue(t.isDone);
    }

    @Test
    public void markAsNotDoneTest() {
        t.markAsNotDone();
        assertFalse(t.isDone);
    }
}

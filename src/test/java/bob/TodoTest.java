package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.tasks.Todo;

public class TodoTest {
    @Test
    public void testGetTaskType() {
        Todo task = new Todo("do this");
        assertEquals("T", task.getTaskType());
    }

    @Test
    public void testString() {
        Todo task = new Todo("do this");
        assertEquals("[T][ ] do this", task.toString());
    }
}

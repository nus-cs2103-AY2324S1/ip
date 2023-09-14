package bob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("Sample Task");
        String expected = "[T][ ] Sample Task";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToFileString() {
        Todo todo = new Todo("Sample Task");
        String expected = "T | 0 | Sample Task";
        assertEquals(expected, todo.toFileString());
    }
}

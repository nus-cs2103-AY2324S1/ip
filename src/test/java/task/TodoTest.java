package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Test Todo");

        String expected = "[T] [ ] Test Todo";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testTodoToFileString() {
        Todo todo = new Todo("Test Todo");

        String expected = "TODO | 0 | Test Todo";
        assertEquals(expected, todo.toFileString());
    }

    @Test
    public void testMarkAsCompleted() {
        Todo todo = new Todo("Test Todo");

        assertFalse(todo.isCompleted());
        todo.setCompleted();
        assertTrue(todo.isCompleted());
    }
}

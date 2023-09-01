package juke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void toString_success() {
        Todo todo = new Todo("testing");
        assertEquals(todo.toString(), "[T][ ] testing");
    }

    @Test
    public void toData_success() {
        Todo todo = new Todo("testing");
        assertEquals(todo.toData(), "T|false|testing");
    }

    @Test
    public void mark_success() {
        Todo todo = new Todo("testing");
        todo.markAsDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void unmark_success() {
        Todo todo = new Todo("testing");
        todo.markAsUndone();
        assertFalse(todo.isDone);
    }
}

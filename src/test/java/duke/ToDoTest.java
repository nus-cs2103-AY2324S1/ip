package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class ToDoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("buy lunch");
        assertEquals("[T][ ] buy lunch", todo.toString());
    }

    @Test
    public void testToFileString() {
        Todo todo = new Todo("go for a walk");
        assertEquals("T | 0 | go for a walk\n", todo.toFileString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("call james");
        todo.markAsDone();
        assertEquals("[T][X] call james", todo.toString());
    }

    @Test
    public void testTodoIsDone() {
        Todo todo = new Todo("Buy groceries");
        assertFalse(todo.isDone(), "A new Todo should not be marked as done");

        todo.markAsDone();
        assertTrue(todo.isDone(), "markAsDone() should set the Todo as done");
    }
}

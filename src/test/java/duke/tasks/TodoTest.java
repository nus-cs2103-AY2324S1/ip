package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testConstructorAndGetters() {
        Todo todo = new Todo("Buy groceries", false, new ArrayList<>());
        assertEquals("Buy groceries", todo.getDescription());
        assertFalse(todo.getIsDone());

        Todo completedTodo = new Todo("Finish assignment", true, new ArrayList<>());
        assertEquals("Finish assignment", completedTodo.getDescription());
        assertTrue(completedTodo.getIsDone());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Read a book", false, new ArrayList<>());
        assertEquals("[T][ ] Read a book", todo.toString());

        Todo completedTodo = new Todo("Clean the room", true, new ArrayList<>());
        assertEquals("[T][X] Clean the room", completedTodo.toString());
    }

    @Test
    public void testWrite() {
        Todo todo = new Todo("Call a friend", false, new ArrayList<>());
        assertEquals("T | 0 | Call a friend", todo.write());

        Todo completedTodo = new Todo("Go for a run", true, new ArrayList<>());
        assertEquals("T | 1 | Go for a run", completedTodo.write());
    }
}

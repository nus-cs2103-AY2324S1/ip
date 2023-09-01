package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testConstructorAndGetters() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("Buy groceries", todo.getDescription());
        assertFalse(todo.getIsDone());

        Todo completedTodo = new Todo("Finish assignment", true);
        assertEquals("Finish assignment", completedTodo.getDescription());
        assertTrue(completedTodo.getIsDone());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());

        Todo completedTodo = new Todo("Clean the room", true);
        assertEquals("[T][X] Clean the room", completedTodo.toString());
    }

    @Test
    public void testWrite() {
        Todo todo = new Todo("Call a friend");
        assertEquals("T | 0 | Call a friend", todo.write());

        Todo completedTodo = new Todo("Go for a run", true);
        assertEquals("T | 1 | Go for a run", completedTodo.write());
    }
}

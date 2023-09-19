package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link ToDo} class.
 */
public class ToDoTest {

    /**
     * Test the {@link ToDo#markDone()} method.
     */
    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("Buy groceries");
        assertFalse(todo.isDone());

        todo.markDone();
        assertTrue(todo.isDone());
    }

    /**
     * Test the {@link ToDo#markUndone()} method.
     */
    @Test
    public void testMarkUndone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markDone();
        assertTrue(todo.isDone());

        todo.markUndone();
        assertFalse(todo.isDone());
    }

    /**
     * Test the {@link ToDo#getName()} method.
     */
    @Test
    public void testGetName() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("Buy groceries", todo.getName());
    }

    /**
     * Test the {@link ToDo#toString()} method.
     */
    @Test
    public void testToString() {
        ToDo todoUndone = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todoUndone.toString());

        ToDo todoDone = new ToDo("Buy groceries");
        todoDone.markDone();
        assertEquals("[T][X] Buy groceries", todoDone.toString());
    }
}

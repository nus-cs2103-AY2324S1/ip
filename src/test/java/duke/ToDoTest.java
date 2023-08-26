package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("Buy groceries");
        assertFalse(todo.isDone());
        
        todo.markDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void testMarkUndone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markDone();
        assertTrue(todo.isDone());
        
        todo.markUndone();
        assertFalse(todo.isDone());
    }

    @Test
    public void testGetName() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("Buy groceries", todo.getName());
    }

    @Test
    public void testToString() {
        ToDo todoUndone = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todoUndone.toString());

        ToDo todoDone = new ToDo("Buy groceries");
        todoDone.markDone();
        assertEquals("[T][X] Buy groceries", todoDone.toString());
    }
}

package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString());
    }

    @Test
    void testMark() {
        ToDo todo = new ToDo("Buy groceries");
        todo.mark();
        assertTrue(todo.done);
    }

    @Test
    void testUnmark() {
        ToDo todo = new ToDo("Buy groceries");
        todo.mark();
        assertTrue(todo.done);
        todo.unMark();
        assertFalse(todo.done);
    }
}


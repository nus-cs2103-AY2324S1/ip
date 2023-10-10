package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
        assertTrue(todo.isMarked());
    }

    @Test
    void testUnmark() {
        ToDo todo = new ToDo("Buy groceries");
        todo.mark();
        assertTrue(todo.isMarked());
        todo.unMark();
        assertFalse(todo.isMarked());
    }
}


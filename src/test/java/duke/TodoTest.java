package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToStringUnmarkedTodo() {
        Todo todo = new Todo("Buy groceries", false);
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToStringMarkedTodo() {
        Todo todo = new Todo("Finish homework", true);
        String expected = "[T][X] Finish homework";
        assertEquals(expected, todo.toString());
    }

}

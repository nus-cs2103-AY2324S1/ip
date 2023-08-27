package taskclasses;

import taskclasses.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TodoTest {
    @Test
    public void testFormatToFile() {
        Todo todo = new Todo("Buy groceries");
        String expected = "T | 0 | Buy groceries";
        assertEquals(expected, todo.formatToFile());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Read a book");
        String expected = "[T][ ] Read a book";
        assertEquals(expected, todo.toString());
    }
}

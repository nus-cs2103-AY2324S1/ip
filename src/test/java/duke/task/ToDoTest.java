package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

    @Test
    public void testFileDescription() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("T | 1 | Read a book" + "\n", todo.toFileFormat());

        todo.markAsDone();
        assertEquals("T | 0 | Read a book" + "\n", todo.toFileFormat());
    }
}

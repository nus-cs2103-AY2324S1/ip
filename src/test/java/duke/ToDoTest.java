package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markAsDone();
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

}

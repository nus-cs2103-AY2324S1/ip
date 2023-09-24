package duke.task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T] [ ] Buy groceries", todo.toString());
    }
}

package duke.task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("test todo");
        assertEquals(todo.getDescription(), "test todo");
    }
}

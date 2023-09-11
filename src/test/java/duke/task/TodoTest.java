package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("test todo");
        assertEquals(todo.getDescription(), "test todo");
    }
}

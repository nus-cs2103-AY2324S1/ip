package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void testMarkTodoAsDone() {
        Todo todo = new Todo("do individual project");
        todo.markAsDone();
        assertTrue(todo.isDone);
    }
}

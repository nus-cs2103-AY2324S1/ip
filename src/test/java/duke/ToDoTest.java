package duke;

import duke.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("[T][ ] Sample Todo", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] Sample Todo", todo.toString());
    }
}
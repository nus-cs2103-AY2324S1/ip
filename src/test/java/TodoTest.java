import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.DukeException;
import task.Todo;

public class TodoTest {
    @Test
    public void printString_markAndUnmark_success() throws DukeException {
        Todo todo = new Todo("xxx");
        assertEquals("[T][ ] xxx", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] xxx", todo.toString());
        todo.unmarkAsDone();
        assertEquals("[T][ ] xxx", todo.toString());
    }
}

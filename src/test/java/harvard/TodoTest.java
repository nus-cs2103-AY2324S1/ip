package harvard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodo() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void testTodoDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void testTodoUndone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals("[T][ ] read book", todo.toString());
    }
}

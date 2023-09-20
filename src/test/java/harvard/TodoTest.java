package harvard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Represents a todo task.
 */
public class TodoTest {
    /**
     * Tests the todo task.
     */
    @Test
    public void testTodo() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }
    /**
     * Tests the todo task.
     */

    @Test
    public void testTodoDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }
    /**
     * Tests the todo task.
     */

    @Test
    public void testTodoUndone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals("[T][ ] read book", todo.toString());
    }
}

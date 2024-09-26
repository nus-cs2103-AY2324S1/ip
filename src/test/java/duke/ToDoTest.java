package duke;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo newTodo = new ToDo("borrow books");
        assertEquals("[T][ ] borrow books", newTodo.toString());

        ToDo done = new ToDo("return books");
        done.markAsDone();
        assertEquals("[T][X] return books", done.toString());
    }

    @Test
    public void testAsMark() {
        ToDo todo = new ToDo("buy lunch");
        assertFalse(todo.isDone());
        todo.markAsDone();
        assertTrue(todo.isDone());
    }
}

package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo = new Todo("task", false);
    @Test
    public void testMarkTodo() {
        assertEquals(true, todo.markTask(), "Marking todo should work");
    }

    @Test
    public void testUnmarkTodo() {
        assertEquals(false, todo.unmarkTask(), "Unmarking todo should work");
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] task", todo.toString(), "todo.toString() should work");
    }
}

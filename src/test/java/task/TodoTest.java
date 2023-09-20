package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void mark_success() throws Exception {
        Todo todo = new Todo("borrow book");
        Todo markedTodo = todo.mark();
        assertEquals("[T][X] borrow book", markedTodo.toString());
    }

    @Test
    public void unmark_success() throws Exception {
        Todo todo = new Todo("borrow book");
        Todo unmarkedTodo = todo.unmark();
        assertEquals("[T][ ] borrow book", unmarkedTodo.toString());
    }

    @Test
    public void saveTask_success() throws Exception {
        Todo todo = new Todo("borrow book");
        String savedTask = todo.saveTask();
        assertEquals("T | 0 | borrow book", savedTask);
    }
}

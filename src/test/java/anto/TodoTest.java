package anto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void createTodoTaskTest() {
        Todo newTodoTask = new Todo("Borrow book");
        assertEquals("[T] [ ] Borrow book", newTodoTask.toString());
    }

    @Test
    public void markAndUnmarkTodoTaskTest() {
        Todo newTodoTask = new Todo("Borrow book");
        newTodoTask.markAsDone();
        assertEquals("X", newTodoTask.getStatusIcon());
        assertEquals("[T] [X] Borrow book", newTodoTask.toString());
        newTodoTask.unmark();
        assertEquals(" ", newTodoTask.getStatusIcon());
        assertEquals("[T] [ ] Borrow book", newTodoTask.toString());
    }
}

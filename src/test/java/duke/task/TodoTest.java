package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void getStatusIcon_todoNotDone_success() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("[ ]", todo.getStatusIcon());
    }

    @Test
    public void getStatusIcon_todoDone_success() {
        Todo todo = new Todo("Sample Todo");
        todo.markAsDone();
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void markAsDone_success() {
        Todo todo = new Todo("Sample Todo");
        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void markAsNotDone_success() {
        Todo todo = new Todo("Sample Todo");
        todo.markAsDone();
        todo.markAsNotDone();
        assertFalse(todo.isDone());
    }

    @Test
    public void toFileString_isNotDone_success() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("T | 0 | Sample Todo", todo.toFileString());
    }

    @Test
    public void toFileString_isDone_success() {
        Todo todo = new Todo("Sample Todo");
        todo.markAsDone();
        assertEquals("T | 1 | Sample Todo", todo.toFileString());
    }

    @Test
    public void toString_isDone_success() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("[T][ ] Sample Todo", todo.toString());
    }

    @Test
    public void toString_isNotDone_success() {
        Todo todo = new Todo("Sample Todo");
        todo.markAsDone();
        assertEquals("[T][X] Sample Todo", todo.toString());
    }
}


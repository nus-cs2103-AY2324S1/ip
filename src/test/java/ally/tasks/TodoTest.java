package ally.tasks; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void getStatusIcon_markAsDone_success() {
        Todo todo = new Todo("Test");
        todo.setMarkDone();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void getStatusIcon_markAsNotDone_success() {
        Todo todo = new Todo("Test");
        todo.setMarkNotDone();
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void markAsDone_success() {
        Todo todo = new Todo("Test");
        todo.setMarkDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void markAsNotDone_success() {
        Todo todo = new Todo("Test");
        todo.setMarkNotDone();
        assertFalse(todo.isDone);
    }

    @Test
    public void fileFormat_success() {
        Todo todo = new Todo("Test");
        String format = todo.formatFile();
        assertEquals("T | 0 | Test", format);
    }

    @Test
    public void toString_success() {
        Todo todo = new Todo("Test");
        String format = todo.toString();
        assertEquals("[T][ ] Test", format);
    }
}

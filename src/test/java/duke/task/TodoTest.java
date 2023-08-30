package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTaskStringify() {
        // isDone is false
        assertEquals("T | 0 | borrow book", new Todo("borrow book", false).taskStringify());

        // isDone is true
        assertEquals("T | 1 | borrow book", new Todo("borrow book", true).taskStringify());
    }

    @Test
    public void testToString() {
        // isDone not given
        assertEquals("[T][ ] borrow book", new Todo("borrow book").toString());

        // isDone is false
        assertEquals("[T][ ] borrow book", new Todo("borrow book", false).toString());

        // isDone is true
        assertEquals("[T][X] borrow book", new Todo("borrow book", true).toString());
    }
}

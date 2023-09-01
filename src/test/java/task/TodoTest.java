package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void mark_success() throws Exception {
        assertEquals("[T][X] borrow book", new Todo("borrow book").mark().toString());
    }

    @Test
    public void unmark_success() throws Exception {
        assertEquals("[T][ ] borrow book", new Todo("borrow book").unmark().toString());
    }

    @Test
    public void saveTask_success() throws Exception {
        assertEquals("T | 0 | borrow book", new Todo("borrow book").saveTask());
    }
}

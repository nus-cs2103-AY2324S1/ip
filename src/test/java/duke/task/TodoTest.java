package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testCreate() {
        Todo t = new Todo("Todo 1");
        assertEquals("Todo 1", t.message);
    }

    @Test
    public void testToSaveFormatString() {
        Todo t = new Todo("Todo 2");
        assertEquals("T | 0 | Todo 2", t.toSaveFormatString());
        t.markAsDone();
        assertEquals("T | 1 | Todo 2", t.toSaveFormatString());
    }

    @Test
    public void testToString() {
        Todo t = new Todo("Message");
        assertEquals("[T][ ] Message", t.toString());
        t.markAsDone();
        assertEquals("[T][X] Message", t.toString());
    }
}

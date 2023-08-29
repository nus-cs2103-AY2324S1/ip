package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void testToStringForSave() {
        assertEquals("T | 0 | read book", new Todo("read book").toStringForSave());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }
}
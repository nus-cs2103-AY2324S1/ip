package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }

    @Test
    public void testToFileString() {
        assertEquals("T | O | borrow book", new ToDo("borrow book").toFileString());
    }

}

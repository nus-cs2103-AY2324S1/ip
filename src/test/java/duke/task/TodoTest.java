package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion(){
        assertEquals("[T][ ] read books",new Todo("read books").toString());
    }

    @Test
    public void testFileRepresentation() {
        assertEquals("T | 0 | read book\n", new Todo("read book").showFileRepresentation());
    }

    @Test
    public void testIsExit() {
        assertEquals(false, new Todo("read book").isDone);
    }
}

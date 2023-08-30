package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test for Todo class
 */
public class TodoTest {

    @Test
    public void testToStringConversion() {
        assertEquals("[T][ ] sleep", new Todo("sleep").toString());
    }

    @Test
    public void testToFileFormat() {
        assertEquals("T | false | sleep", new Todo("sleep").toFileFormat());
    }
}

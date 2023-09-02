package grumpygordon.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test class for Todo.
 */
public class TodoTest {
    /**
     * Tests the string conversion of Todo.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][X] Bake cake", new Todo("Bake cake", true).toString());
        assertEquals("[T][ ] Sleep", new Todo("Sleep", false).toString());
    }

    /**
     * Tests the save format conversion of Todo.
     */
    @Test
    public void testSaveFormatConversion() {
        assertEquals("T | 1 | Bake cake", new Todo("Bake cake", true).toSaveFormat());
        assertEquals("T | 0 | Sleep", new Todo("Sleep", false).toSaveFormat());
    }
}

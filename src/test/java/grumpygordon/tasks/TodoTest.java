package grumpygordon.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][X] Bake cake", new Todo("Bake cake", true).toString());
        assertEquals("[T][ ] Sleep", new Todo("Sleep", false).toString());
    }

    @Test
    public void testSaveFormatConversion() {
        assertEquals("T | 1 | Bake cake", new Todo("Bake cake", true).toSaveFormat());
        assertEquals("T | 0 | Sleep", new Todo("Sleep", false).toSaveFormat());
    }
}

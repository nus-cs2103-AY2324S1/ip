package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParseFileInput_TodoTask() {
        String line = "T | 0 | Buy groceries";

        Task result = Parser.parseFileInput(line);

        assertTrue(result instanceof ToDos);
        assertEquals("Buy groceries", result.getDescription());
        assertFalse(result.isDone());
    }

}
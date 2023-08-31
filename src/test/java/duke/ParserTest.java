package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The ParserTest class contains test cases for the Parser class in the Duke application.
 * It verifies the correctness of the parseFileInput method using JUnit tests.
 */
public class ParserTest {
    /**
     * Tests the parsing of a "todos" task representation from a file input string.
     */
    @Test
    public void testParseFileInput_TodoTask() {
        String line = "T | 0 | Buy groceries";

        Task result = Parser.parseFileInput(line);

        assertTrue(result instanceof ToDos);
        assertEquals("Buy groceries", result.getDescription());
        assertFalse(result.isDone());
    }

}
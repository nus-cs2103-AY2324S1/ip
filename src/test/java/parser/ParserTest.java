package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The ParserTest class contains unit tests for the Parser class.
 * These tests verify the functionality of the Parser class methods.
 */
public class ParserTest {

    /**
     * This test method verifies the correctness of the taskName method in the Parser class.
     * It checks whether the taskName method correctly extracts a task name from a given input string
     * in a specific format.
     *
     * Test Input: "todo 123"
     * Expected Output: "123"
     */
    @Test
    public void testList() {
        // Arrange
        Parser parser = new Parser();

        // Act
        String taskName = parser.taskName("todo 123");

        // Assert
        assertEquals("123", taskName);
    }
}

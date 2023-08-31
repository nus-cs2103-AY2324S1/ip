package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TaskTest class contains test cases for the Task class in the Duke application.
 * It verifies the correctness of the toFileString method using JUnit tests.
 */
public class TaskTest {
    /**
     * Tests the conversion of a task to its file representation string.
     */
    @Test
    public void testFileStringConversion() {
        Task task = new Task("Test");
        task.markAsDone();
        assertEquals("  | 1 | Test", task.toFileString());
    }
}

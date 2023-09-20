package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The ToDoTest class contains unit tests for the ToDo class.
 */
public class ToDoTest {
    /**
     * Tests the toFileString() function of the ToDo task to ensure it returns the correct formatted string.
     */
    @Test
    public void testToFileString() {
        ToDo task = new ToDo("Buy groceries", true);
        assertEquals("T | 1 | Buy groceries", task.toFileString());
    }

    /**
     * Test the toString method of the ToDo class.
     */
    @Test
    public void testToString() {
        ToDo testTask = new ToDo("Test Todo");
        assertEquals("[T][ ] Test Todo", testTask.toString());
    }

}

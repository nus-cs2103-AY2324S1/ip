package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        try {
            Todo todo = new Todo("buy groceries");
            assertEquals("[T][ ] buy groceries []", todo.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Todo todo = new Todo("submit assignment", true, "school", "urgent");
            assertEquals("[T][X] submit assignment [school, urgent]", todo.toString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void testFileStringConversion() {
        try {
            Todo todo = new Todo("buy groceries", false, "chore");
            assertEquals("T | 0 | buy groceries | [chore]", todo.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }

        try {
            Todo todo = new Todo("submit assignment", true, "school", "urgent");
            assertEquals("T | 1 | submit assignment | [school, urgent]", todo.toFileString());
        } catch (Exception e) {
            // the test should not reach this line
        }
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        try {
            new Todo("");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPSIE!!! The description of a task cannot be empty ˃̵ᴗ˂̵", e.getMessage());
        }
    }
}

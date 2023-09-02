package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test class for ToDo
 */
public class ToDoTest {
    /**
     * Tests string conversion of ToDo to a string.
     */
    @Test
    public void testToString() {
        assertEquals("[T][ ] Sleep", new ToDo("Sleep").toString());
    }

    /**
     * Tests marking and unmarking of ToDo.
     */
    @Test
    public void testMarkDoneAndUndone() {
        ToDo todo = new ToDo("Run");
        todo.markAsDone();
        assertEquals("[T][X] Run", todo.toString());
        todo.markAsUndone();
        assertEquals("[T][ ] Run", todo.toString());
    }
}

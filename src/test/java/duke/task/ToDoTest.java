package duke.task;

import org.junit.jupiter.api.Test;

/**
 * The ToDoTest class contains unit tests for the ToDo class.
 */
public class ToDoTest {

    /**
     * Test the toString method of the ToDo class.
     */
    @Test
    public void testToString() {
        ToDo testTask = new ToDo("Test ToDo");
        assertEquals("[T][ ] Test ToDo", testTask.toString());
    }

    /**
     * Test the markAsDone method of the ToDo class.
     */
    @Test
    public void testMarkAsDone() {
        ToDo task1 = new ToDo("Test ToDo");
        task1.markAsDone();
        assertEquals("[T][X] Test ToDo", task1.toString());
    }
}

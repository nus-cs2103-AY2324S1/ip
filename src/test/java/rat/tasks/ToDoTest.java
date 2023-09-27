package rat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class tests the ToDo class.
 * Checks if creating, formatting, and updating operations work as intended.
 * @author Keagan
 */
public class ToDoTest {

    /**
     * Tests the creation of a ToDo task.
     */
    @Test
    public void testToDo_create() {
        ToDo tdTest = new ToDo("test");
        assertEquals("[T][ ] test", tdTest.toString());
    }

    /**
     * Tests the formatting of a ToDo task for file storage.
     */
    @Test
    public void testToDo_formatForFile() {
        ToDo tdTest = new ToDo("test");
        assertEquals("T, 0, test", tdTest.formatForFile());
    }

    /**
     * Tests the marking of a ToDo task as done.
     */
    @Test
    public void testToDo_markDone() {
        ToDo tdTest = new ToDo("test");
        tdTest.markDone();
        assertEquals("[T][X] test", tdTest.toString());
    }

    /**
     * Tests the unmarking of a ToDo task as done.
     */
    @Test
    public void testToDo_unmarkDone() {
        ToDo tdTest = new ToDo("test");
        tdTest.markDone();
        tdTest.unmarkDone();
        assertEquals("[T][ ] test", tdTest.toString());
    }
}

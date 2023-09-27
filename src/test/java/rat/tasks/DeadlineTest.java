package rat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class tests the Deadline class.
 * Checks if creating, formatting, and updating operations work as intended.
 * @author Keagan
 */
public class DeadlineTest {

    /**
     * Tests the creation of a Deadline task.
     */
    @Test
    public void testDeadline_create() {
        Deadline dlTest = new Deadline("01/01/2023 00:00", "test");
        assert (dlTest.toString().equals("[D][ ] test (by: Sun, 1 Jan 2023 00:00)"));
    }

    /**
     * Tests the formatting of a Deadline task for file storage.
     */
    @Test
    public void testDeadline_formatForFile() {
        Deadline dlTest = new Deadline("01/01/2023 00:00", "test");
        assertEquals("D, 0, test, 2023-01-01T00:00", dlTest.formatForFile());
    }

    /**
     * Tests the marking of a Deadline task as done.
     */
    @Test
    public void testDeadline_markDone() {
        Deadline dlTest = new Deadline("01/01/2023 00:00", "test");
        dlTest.markDone();
        assertEquals("[D][X] test (by: Sun, 1 Jan 2023 00:00)", dlTest.toString());
    }

    /**
     * Tests the unmarking of a Deadline task as done.
     */
    @Test
    public void testDeadline_unmarkDone() {
        Deadline dlTest = new Deadline("01/01/2023 00:00", "test");
        dlTest.markDone();
        dlTest.unmarkDone();
        assertEquals("[D][ ] test (by: Sun, 1 Jan 2023 00:00)", dlTest.toString());
    }
}

package rat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rat.storage.RatStorage;
import rat.tasks.RatTaskManager;

/**
 * This class tests the AddCommand class, together with its subclasses.
 * @author Keagan
 */
public class AddCommandTest {

    /**
     * The RatTaskManager object used for testing.
     */
    protected RatTaskManager rtmTest = new RatTaskManager(new RatStorage("testdata/blank.txt"));

    /**
     * Tests the creation and adding of a ToDo task.
     */
    @Test
    public void testAddToDo() {
        AddCommand addTest = new AddCommand(rtmTest, "todo test", CommandType.TODO);
        addTest.execute();
        assertEquals("1. [T][ ] test", rtmTest.toString().trim());
        rtmTest.deleteAll();
    }

    /**
     * Tests the creation and adding of a Deadline task.
     */
    @Test
    public void testAddDeadline() {
        AddCommand addTest = new AddCommand(rtmTest, "deadline test /by 01/01/2023 00:00", CommandType.DEADLINE);
        addTest.execute();
        assertEquals("1. [D][ ] test (by: Sun, 1 Jan 2023 00:00)", rtmTest.toString().trim());
        rtmTest.deleteAll();
    }

    /**
     * Tests the creation and adding of an Event task.
     */
    @Test
    public void testAddEvent() {
        AddCommand addTest = new AddCommand(rtmTest,
                "event test /from 01/01/2023 00:00 /to 01/01/2023 01:00", CommandType.EVENT);
        addTest.execute();
        assertEquals("1. [E][ ] test (from: Sun, 1 Jan 2023 00:00 to: Sun, 1 Jan 2023 01:00)",
                rtmTest.toString().trim());
        rtmTest.deleteAll();
    }

}

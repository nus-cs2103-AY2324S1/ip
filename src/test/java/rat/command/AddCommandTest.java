package rat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rat.storage.RatStorage;
import rat.tasks.RatTaskManager;

public class AddCommandTest {

    protected RatTaskManager rtmTest = new RatTaskManager(new RatStorage("testdata/blank.txt"));

    @Test
    public void testAddToDo() {
        AddCommand addTest = new AddCommand(rtmTest, "todo test", CommandType.TODO);
        addTest.execute();
        assertEquals("1. [T][ ] test", rtmTest.toString().trim());
        rtmTest.deleteAll();
    }

    @Test
    public void testAddDeadline() {
        AddCommand addTest = new AddCommand(rtmTest, "deadline test /by 01/01/2023 00:00", CommandType.DEADLINE);
        addTest.execute();
        assertEquals("1. [D][ ] test (by: Sun, 1 Jan 2023 00:00)", rtmTest.toString().trim());
        rtmTest.deleteAll();
    }

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

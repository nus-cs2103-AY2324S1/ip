package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the parseTaskFromLine method of the Storage class.
 */
public class StorageTest {

    private Storage storage = new Storage();


    /**
     * Tests the method with a valid ToDo data.
     *
     * @throws DukeException If the tested method throws DukeException.
     */
    @Test
    public void parseTaskFromLine_validToDoData_returnsToDoTask() throws DukeException {
        String line = "T | 0 | Task 1";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[T][ ] Task 1", task.toString());
    }

    /**
     * Tests the method with valid Deadline data.
     *
     * @throws DukeException If the tested method throws DukeException.
     */
    @Test
    public void parseTaskFromLine_validDeadlineData_returnsDeadlineTask() throws DukeException {
        String line = "D | 1 | Task 2 | 2019-10-15";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[D][X] Task 2 (by: Oct 15 2019)", task.toString());
    }

    /**
     * Tests the method with a valid Event data.
     *
     * @throws DukeException If the tested method throws DukeException.
     */
    @Test
    public void parseTaskFromLine_validEventData_returnsEventTask() throws DukeException {
        String line = "E | 1 | Task 3 | Aug 6th 2pm-4pm";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[E][X] Task 3 (from: Aug 6th 2pm to: 4pm)", task.toString());
    }

    /**
     * Tests the method with an invalid data to check whether it throws
     * the correct DukeException error or not.
     */
    @Test
    public void parseTaskFromLine_invalidData_throwsDukeException() {
        try {
            String line = "Invalid Data";
            Task task = storage.parseTaskFromLine(line);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid data format in the file.", e.getMessage());
        }
    }
}

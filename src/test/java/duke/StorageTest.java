package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private Storage storage = new Storage("");

    @Test
    public void parseTaskFromLine_validToDoData_returnsToDoTask() throws DukeException {
        String line = "T | 0 | Task 1";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[T][ ] Task 1", task.toString());
    }

    @Test
    public void parseTaskFromLine_validDeadlineData_returnsDeadlineTask() throws DukeException {
        String line = "D | 1 | Task 2 | 2019-10-15";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[D][X] Task 2 (by: Oct 15 2019)", task.toString());
    }

    @Test
    public void parseTaskFromLine_validEventData_returnsEventTask() throws DukeException {
        String line = "E | 1 | Task 3 | Aug 6th 2pm-4pm";
        Task task = storage.parseTaskFromLine(line);

        assertEquals("[E][X] Task 3 (from: Aug 6th 2pm to: 4pm)", task.toString());
    }

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

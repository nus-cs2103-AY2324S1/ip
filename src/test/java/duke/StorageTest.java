package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Task;

/**
 * Tests the Storage class.
 */
public class StorageTest {

    /**
     * Tests the loadTaskList method.
     */
    @Test
    public void loadTaskList_savedFile_success() {
        // saved list is loaded
        assertEquals("T | N | Homework\n"
                        + "D | Y | Project | 2023-11-11\n",
                listToString(new Storage(Paths.get(".", "testdata", "testlist.txt")).loadTaskList()));
    }

    /**
     * Tests the loadTaskList method.
     */
    @Test
    public void loadTaskList_wrongPath_emptyList() {
        // return empty list if path is wrong
        assertEquals("", listToString(new Storage(null).loadTaskList()));
    }

    /**
     * Converts an ArrayList of tasks to a String.
     * @param list The ArrayList of tasks.
     * @return A String representing the list.
     */
    public String listToString(ArrayList<Task> list) {
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            toWrite.append(list.get(i).taskToString());
            toWrite.append("\n");
        }
        return toWrite.toString();
    }

    /**
     * Tests the stringToTask method.
     */
    @Test
    public void stringToTask_multipleWhitespaceEntry_success() {
        try {
            // accept tasks with descriptions of multiple words
            assertEquals(new Deadline("Finish coding this assignment", LocalDate.parse("2023-10-10")),
                    new Storage(Paths.get(".", "testdata", "testlist.txt"))
                            .stringToTask("D | N | Finish coding this assignment | 2023-10-10"));
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests the stringToTask method.
     */
    @Test
    public void stringToTask_markedEntry_success() {
        try {
            Deadline d = new Deadline("Finish coding this assignment", LocalDate.parse("2023-10-10"));
            d.markAsDone();

            // tasks should be correctly displayed as marked
            assertEquals(d, new Storage(Paths.get(".", "testdata", "testlist.txt"))
                    .stringToTask("D | Y | Finish coding this assignment | 2023-10-10"));
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests the stringToTask method.
     */
    @Test
    public void stringToTask_corruptedEntry_exceptionThrown() {
        try {
            assertEquals(new Deadline("Finish coding this assignment", LocalDate.parse("2023-10-10")),
                    new Storage(Paths.get(".", "data", "duke.txt"))
                            .stringToTask("D | N"));
            fail(); // test should not reach this line
        } catch (DukeException e) {
            // saved entry is of incorrect format
            assertEquals("Data file corrupted.", e.getMessage());
        }
    }

    /**
     * Tests the stringToTask method.
     */
    @Test
    public void stringToTask_invalidTaskType_exceptionThrown() {
        try {
            assertEquals(new Deadline("Finish coding this assignment", LocalDate.parse("2023-10-10")),
                    new Storage(Paths.get(".", "data", "duke.txt"))
                            .stringToTask("XXX | N | Finish coding this assignment | 2023-10-10"));
            fail(); // test should not reach this line
        } catch (DukeException e) {
            // saved entry has unknown symbol
            assertEquals("Data file corrupted.", e.getMessage());
        }
    }
}

package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTaskException;
import duke.exception.NoEndDateException;

/**
 * The {@code StorageTest} class contains JUnit tests for the {@link Storage} class.
 * It tests the loading of task data from a storage file.
 */
public class StorageTest {

    /**
     * Tests loading a 'todo' task from a given String representation.
     *
     * @throws DukeException If there is an issue with task loading.
     */
    @Test
    public void testLoadTodo() throws DukeException {
        Storage storage = new Storage("");
        String test1 = "T | 1 | read book";
        Task output1 = new ToDo("read book");
        output1.markAsDone();
        assertEquals(output1.toString(), storage.loadData(test1).toString());
    }

    /**
     * Tests loading a 'dealine' task from a given String representation.
     *
     * @throws DukeException If there is an issue with task loading.
     */
    @Test
    public void testLoadDealine() throws DukeException {
        Storage storage = new Storage("");
        String test2 = "D | 0 | return book | June 6th";
        Task output2 = new Deadline("return book", "June 6th");
        assertEquals(output2.toString(), storage.loadData(test2).toString());
    }

    /**
     * Tests loading a 'even' task from a given String representation.
     *
     * @throws DukeException If there is an issue with task loading.
     */
    @Test
    public void testLoadEvent() throws DukeException {
        Storage storage = new Storage("");
        String test3 = "E | 0 | project meeting | Aug 6th 2pm | 4pm";
        Task output3 = new Event("project meeting", "Aug 6th 2pm", "4pm");
        assertEquals(output3.toString(), storage.loadData(test3).toString());
    }

    /**
     * Tests loading an invalid string representation of a task.
     *
     * @throws DukeException If there is an issue with task loading.
     */
    @Test
    public void testInvalidData() throws DukeException {
        Storage storage = new Storage("");
        String test4 = "T | 1 |";
        assertThrows(EmptyTaskException.class, () -> storage.loadData(test4));

        String test5 = "D | 0 | return book |";
        assertThrows(EmptyDateException.class, () -> storage.loadData(test5));

        String test6 = "E | 0 | project meeting | Aug 6th 2pm | ";
        assertThrows(NoEndDateException.class, () -> storage.loadData(test6));
    }
}

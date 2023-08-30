package duke;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTaskException;
import duke.exception.NoEndDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void testLoadTodo() throws DukeException {
        Storage storage = new Storage("");
        String test1 = "T | 1 | read book";
        Task output1 = new ToDo("read book");
        output1.markAsDone();
        assertEquals(output1.toString(), storage.loadData(test1).toString());
    }

    @Test
    public void testLoadDealine() throws DukeException {
        Storage storage = new Storage("");
        String test2 = "D | 0 | return book | June 6th";
        Task output2 = new Deadline("return book", "June 6th");
        assertEquals(output2.toString(), storage.loadData(test2).toString());
    }

    @Test
    public void testLoadEvent() throws DukeException {
        Storage storage = new Storage("");
        String test3 = "E | 0 | project meeting | Aug 6th 2pm | 4pm";
        Task output3 = new Event("project meeting", "Aug 6th 2pm", "4pm");
        assertEquals(output3.toString(), storage.loadData(test3).toString());
    }

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

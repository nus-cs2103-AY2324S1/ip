package Duke.Storage;

import Duke.Duke;
import Duke.Exceptions.DukeException;
import Duke.Tasks.EventTask;
import Duke.Tasks.Task;
import Duke.Tasks.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    /**
     * Single line import
     *
     * @throws IOException
     */
    @Test
    public void testParsing1() throws DukeException {
        String inputString = "E | 1 | hello! | 2023-01-01T09:30 | 2023-02-02T15:30";

        Task expectedTask = new EventTask("hello!",
                LocalDateTime.parse("2023-01-01T09:30"),
                LocalDateTime.parse("2023-02-02T15:30"));
        expectedTask.setDone();
        Task actualTask = Storage.parseTask(inputString);
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void testParsing2() throws DukeException {
        String inputString = "T | 0 | hello!";

        Task expectedTask = new TodoTask("hello!");
        Task actualTask = Storage.parseTask(inputString);
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void testParsing3() throws DukeException {
        String inputString = "E | 1 | hello! | 2023-01-01T09:30";
        Exception exception = assertThrows(DukeException.class, () -> {
            Storage.parseTask(inputString);
        });

        String expectedMessage = "Invalid file format!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }


}

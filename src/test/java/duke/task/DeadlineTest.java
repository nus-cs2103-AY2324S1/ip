package duke.task;

import duke.Duke;
import duke.task.Task;
import duke.task.Deadline;
import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructor_validDescAndDate_success() throws DukeException {
        Deadline deadline = new Deadline("Complete task", "2023-09-30");
        assertEquals("Complete task", deadline.description);
        assertEquals(LocalDate.of(2023, 9, 30), deadline.by);
        assertEquals(false, deadline.isDone);
    }

    @Test
    public void constructor_wrongDate_exceptionThrown() {
        try {
            new Deadline("Complete task", "30-09-2023");
        } catch (DukeException e) {
            assertEquals("Incorrect date format. Please enter date in yyyy-mm-dd format", e.getMessage());
        }
    }

    @Test
    public void toStringTest() throws DukeException {
        Deadline deadline = new Deadline("Complete task", "2023-09-30");
        String expected = "[D][ ] Complete task (by: Sep 30 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toDataTest() throws DukeException {
        Deadline deadline = new Deadline("Complete task", "2023-09-30");
        String expected = "D | 0 | Complete task | 2023-09-30";
        assertEquals(expected, deadline.toData());
    }
}
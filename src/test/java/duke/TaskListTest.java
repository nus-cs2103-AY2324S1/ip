package duke;

import Exceptions.DukeArgumentException;
import org.junit.jupiter.api.Test;

import static duke.Ui.horizontalLine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void markTask_success() {
        Task exampleTask = new Todo("testing for marking");
        exampleTask.markAsDone();
        assertTrue(exampleTask.isDone);
    }

    @Test
    public void addEvent_invalidDescription_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.eventTask("wedding /from 2023-09-09T09:00:00 /to");
        } catch (DukeArgumentException e) {
            String message = "     OOPS!!! The details for the event is missing!";
            assertEquals(e.getMessage(), message);
        }
    }
}

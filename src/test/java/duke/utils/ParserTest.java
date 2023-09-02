package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Task;

public class ParserTest {
    @Test
    public void parseTask_todo_success() throws DukeException {
        Task task = new Parser().parseTask("T|0|test");
        assertEquals(task.toString(), "[T][ ] test");
    }

    @Test
    public void parseTask_deadline_success() throws DukeException {
        Task task = new Parser().parseTask("D|0|return book|2020-10-10 12:42");
        assertEquals(task.toString(), "[D][ ] return book (by: Oct 10 2020 12:42 PM)");
    }

    @Test
    public void parseTask_event_success() throws DukeException {
        Task task = new Parser().parseTask("E|0|second event|2020-12-20 12:32|2020-12-20 12:33");
        assertEquals(task.toString(), "[E][ ] second event (from: Dec 20 2020 12:32 PM to Dec 20 2020 12:33 PM)");
    }

    @Test
    public void parseTask_invalidTaskType_exceptionThrown() {
        try {
            Task task = new Parser().parseTask("X|0|test");
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

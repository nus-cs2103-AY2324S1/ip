package duke.task;

import duke.Duke;
import duke.exception.DukeException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addDeadline_invalidDeadline_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleDeadline("deadline run");
            fail();
        } catch (DukeException e) {
            assertEquals("You are missing either a valid deadline description or deadline. "
                    + "Please enter a valid deadline description or deadline.", e.getMessage());
        }
    }

    @Test
    public void addDeadline_invalidDeadlineSyntax_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleDeadline("deadline run /by 01-01-2024");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please ensure that the deadline provided is in YYYY-MM-DD format.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addEvent_invalidFromTo_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleEvent("event party /from Weds ");
            fail();
        } catch (DukeException e) {
            assertEquals("You are missing either a valid event description or start and end time. "
                    + "Please enter a valid event description or start and end time.", e.getMessage());
        }
    }
}

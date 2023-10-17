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

    @Test
    public void addRecurring_invalidRecurrence_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleRecur("recur quiz /every ");
        } catch (DukeException e) {
            assertEquals("You are missing either a valid recurring description or recurrence. "
                    + "Please enter a valid recurring description or recurrence.", e.getMessage());
        }
    }

    @Test
    public void markTask_OutOfBoundsIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleMark("mark 6");
        } catch (DukeException e) {
            assertEquals("There is no such task index. Please enter a valid index.",
                    e.getMessage());
        }
    }

    @Test
    public void unmarkTask_invalidIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleMark("unmark q");
        } catch (DukeException e) {
            assertEquals("You did not enter a valid index. Please enter a valid index to mark or unmark.",
                    e.getMessage());
        }
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.handleDelete("delete ");
        } catch (DukeException e) {
            assertEquals("You did not enter an index. Please enter a valid index to delete.",
                    e.getMessage());
        }
    }

    @Test
    public void findTask_invalidTask_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.find("find ");
        } catch (DukeException e) {
            assertEquals("Please enter a task to find", e.getMessage());
        }
    }

    @Test
    public void findTask_noSuchTask_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            taskList.find("find run");
        } catch (DukeException e) {
            assertEquals("There is no such task.", e.getMessage());
        }
    }
}

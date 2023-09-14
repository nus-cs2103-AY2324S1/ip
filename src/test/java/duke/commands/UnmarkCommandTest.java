package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.tasks.Todo;

public class UnmarkCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `unmark`!",
            "Usage: unmark <TASK_NUMBER>"
    );

    @Test
    public void run_markUndoneEvent_success() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        todo.markAsDone();
        tasks.add(todo);
        Command command = new UnmarkCommand("unmark 1");

        CommandResult result = command.run(tasks);

        assertFalse(todo.isDone());
        assertEquals(List.of("OK, I've marked this task as not done yet:", "[T][ ] read book"), result.getResponse());
    }

    @Test
    public void run_markDoneEvent_commandExceptionThrown() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        tasks.add(todo);

        try {
            Command command = new UnmarkCommand("unmark 1");

            command.run(tasks);
        } catch (CommandException e) {
            assertFalse(todo.isDone());
            assertEquals("Task has not been done yet!", e.getMessage());
        }
    }

    @Test
    public void run_missingArgTaskNum_commandExceptionThrown() {
        try {
            new UnmarkCommand("unmark");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgTaskNumWithSpace_commandExceptionThrown() {
        try {
            new UnmarkCommand("unmark ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_taskNumIsNotNumber_commandExceptionThrown() {
        try {
            new UnmarkCommand("unmark abc");
        } catch (CommandException e) {
            assertEquals("Task number must be a number !", e.getMessage());
        }
    }

    @Test
    public void run_markInvalidTaskNumZero_commandExceptionThrown() {
        TaskList tasks = new TaskList();

        try {
            Command command = new UnmarkCommand("unmark 0");

            command.run(tasks);
        } catch (CommandException e) {
            assertEquals("Invalid task number!", e.getMessage());
        }
    }

    @Test
    public void run_markInvalidTaskNumOutOfBounds_commandExceptionThrown() {
        TaskList tasks = new TaskList();

        try {
            Command command = new UnmarkCommand("unmark 3");

            command.run(tasks);
        } catch (CommandException e) {
            assertEquals("Invalid task number!", e.getMessage());
        }
    }
}

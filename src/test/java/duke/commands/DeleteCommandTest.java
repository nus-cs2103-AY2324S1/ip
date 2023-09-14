package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.tasks.Todo;

public class DeleteCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `delete`!",
            "Usage: delete <TASK_NUMBER>"
    );

    @Test
    public void run_deleteEvent_success() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        tasks.add(todo);
        Command command = new DeleteCommand("delete 1");

        CommandResult result = command.run(tasks);

        assertEquals(0, tasks.size());
        assertEquals(
                List.of("Noted. I've removed this task:", todo.toString(), "You have 0 tasks in your list."),
                result.getResponse()
        );
    }

    @Test
    public void run_missingArgTaskNum_commandExceptionThrown() {
        try {
            new DeleteCommand("delete");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgTaskNumWithSpace_commandExceptionThrown() {
        try {
            new DeleteCommand("delete ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_taskNumIsNotNumber_commandExceptionThrown() {
        try {
            new DeleteCommand("delete abc");
        } catch (CommandException e) {
            assertEquals("Task number must be a number !", e.getMessage());
        }
    }

    @Test
    public void run_markInvalidTaskNumZero_commandExceptionThrown() {
        TaskList tasks = new TaskList();

        try {
            Command command = new DeleteCommand("delete 0");

            command.run(tasks);
        } catch (CommandException e) {
            assertEquals("Invalid task number!", e.getMessage());
        }
    }

    @Test
    public void run_markInvalidTaskNumOutOfBounds_commandExceptionThrown() {
        TaskList tasks = new TaskList();

        try {
            Command command = new DeleteCommand("delete 3");

            command.run(tasks);
        } catch (CommandException e) {
            assertEquals("Invalid task number!", e.getMessage());
        }
    }
}

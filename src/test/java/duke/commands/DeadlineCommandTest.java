package duke.commands;

import duke.TaskList;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineCommandTest {
    private static final String invalidFormatMessage = String.join("\n", "Invalid format for command `deadline`!", "Usage: deadline <DESCRIPTION> /by <DEADLINE>");

    @Test
    public void run_validDeadline_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new DeadlineCommand("deadline assignment /by tmr");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of("Got it. I've added this task:", "[D][ ] assignment (by tmr)", "Now you have 1 task in the list."), result.response);

        Task todo = tasks.get(0);
        assertFalse(todo.isDone());
    }

    @Test
    public void run_missingArguments_commandExceptionThrown() {
        try {
            new DeadlineCommand("deadline");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgDescription_commandExceptionThrown() {
        try {
            new DeadlineCommand("deadline /by tmr");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgDeadline_commandExceptionThrown() {
        try {
            new DeadlineCommand("deadline assignment");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }


    @Test
    public void run_emptyDescription_commandExceptionThrown() {
        try {
            new DeadlineCommand("deadline  /by tmr");
        } catch (CommandException e) {
            assertEquals("Deadline description cannot be empty!", e.getMessage());
        }
    }

}

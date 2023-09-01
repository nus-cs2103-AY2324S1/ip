package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.tasks.Task;

public class DeadlineCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `deadline`!",
            "Usage: deadline <DESCRIPTION> /by <DUE_DATE>",
            "<DUE_DATE> should be of the format YYYY-MM-DDTHH:mm[:ss.sss]"
    );

    @Test
    public void run_validDeadline_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new DeadlineCommand("deadline assignment /by 2023-09-10T12:00");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of(
                "Got it. I've added this task:",
                "[D][ ] assignment (by 2023-09-10T12:00)",
                "Now you have 1 task in the list."
        ), result.response);

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

package duke.commands;

import duke.TaskList;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventCommandTest {
    private static final String invalidFormatMessage = String.join("\n", "Invalid format for command `event`!", "Usage: event <DESCRIPTION> [/from <START_TIME> | /to <END_TIME>] [/to <END_TIME> | /from <START_TIME>]");

    @Test
    public void run_validEvent_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new EventCommand("event meeting /from today 12pm /to today 3pm");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of("Got it. I've added this task:", "[E][ ] meeting (from today 12pm to today 3pm)", "Now you have 1 task in the list."), result.response);

        Task event = tasks.get(0);
        assertFalse(event.isDone());
    }

    @Test
    public void run_toBeforeFrom_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new EventCommand("event meeting /to today 3pm /from today 12pm");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of("Got it. I've added this task:", "[E][ ] meeting (from today 12pm to today 3pm)", "Now you have 1 task in the list."), result.response);

        Task todo = tasks.get(0);
        assertFalse(todo.isDone());
    }

    @Test
    public void run_missingArguments_commandExceptionThrown() {
        try {
            new EventCommand("event");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgDescription_commandExceptionThrown() {
        try {
            new EventCommand("event /from today 12pm /to today 3pm");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgStartTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /to today 3pm");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from today 12pm");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_emptyDescription_commandExceptionThrown() {
        try {
            new EventCommand("event  /from today 12pm /to today 3pm");
        } catch (CommandException e) {
            assertEquals("Event description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void run_emptyStartTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from /to today 3pm");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_emptyEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from today 12pm /to ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }
}

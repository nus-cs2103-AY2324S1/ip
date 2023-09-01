package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.tasks.Task;

public class EventCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `event`!",
            "Usage: event <DESCRIPTION> [/from <START_TIME> | /to <END_TIME>] [/to <END_TIME> | /from <START_TIME>]",
            "<START_TIME> and <END_TIME> should be of the format YYYY-MM-DDTHH:mm[:ss.sss]"
    );

    @Test
    public void run_validEvent_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new EventCommand("event meeting /from 2023-09-10T12:00 /to 2023-09-10T15:00");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of(
                "Got it. I've added this task:",
                "[E][ ] meeting (from 2023-09-10T12:00 to 2023-09-10T15:00)",
                "Now you have 1 task in the list."
        ), result.response);

        Task event = tasks.get(0);
        assertFalse(event.isDone());
    }

    @Test
    public void run_toBeforeFrom_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new EventCommand("event meeting /to 2023-09-10T15:00 /from 2023-09-10T12:00");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(List.of(
                "Got it. I've added this task:",
                "[E][ ] meeting (from 2023-09-10T12:00 to 2023-09-10T15:00)",
                "Now you have 1 task in the list."
        ), result.response);

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
            new EventCommand("event /from 2023-09-10T12:00 /to 2023-09-10T15:00");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgStartTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /to 2023-09-10T15:00");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from to 2023-09-10T12:00");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_emptyDescription_commandExceptionThrown() {
        try {
            new EventCommand("event  /from 2023-09-10T12:00 /to 2023-09-10T15:00");
        } catch (CommandException e) {
            assertEquals("Event description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void run_emptyStartTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from /to 2023-09-10T15:00");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_emptyEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from 2023-09-10T12:00 /to ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_invalidStartAndEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from today 12pm /to today 3pm");
        } catch (CommandException e) {
            assertEquals("Event start or end time is not a valid datetime!", e.getMessage());
        }
    }


    @Test
    public void run_invalidStartTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from today 12pm /to 2023-09-10T15:00");
        } catch (CommandException e) {
            assertEquals("Event start or end time is not a valid datetime!", e.getMessage());
        }
    }

    @Test
    public void run_invalidEndTime_commandExceptionThrown() {
        try {
            new EventCommand("event meeting /from 2023-09-10T12:00 /to today 3pm");
        } catch (CommandException e) {
            assertEquals("Event start or end time is not a valid datetime!", e.getMessage());
        }
    }
}

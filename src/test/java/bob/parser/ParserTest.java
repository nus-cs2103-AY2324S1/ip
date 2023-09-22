package bob.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.data.exception.DukeException;

public class ParserTest {
    private static final String INPUT_TASK_NUMBER = "Input the task number";
    private static final String INVALID_COMMAND = "Invalid command";
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("invalidCommand test test", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_badByeCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("byelol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_badListCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("listlol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_emptyMarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("mark", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsMarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("mark 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputMarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("mark lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyUnmarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("unmark", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsUnmarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("unmark 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputUnmarkCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("unmark lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyDeleteCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("delete", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsDeleteCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("delete 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputDeleteCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("delete lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyFindCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("find", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Input something to search for."));
    }

    @Test
    public void parse_emptyTodoCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("todo", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of a todo cannot be empty"));
    }

    @Test
    public void parse_emptyDeadlineCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("deadline", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of a deadline cannot be empty"));
    }

    @Test
    public void parse_emptyEventCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("event", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of an event cannot be empty"));
    }

    @Test
    public void parse_emptyUpdateCommand_exceptionThrown() {
        DukeException thrown = assertThrows(
                DukeException.class, () -> Parser.parse("update", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Input the task you would like to update."));
    }
}

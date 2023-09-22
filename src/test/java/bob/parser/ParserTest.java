package bob.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bob.data.command.Command;
import bob.data.exception.DukeException;

public class ParserTest {
    private static final String INPUT_TASK_NUMBER = "Input the task number";
    private static final String INVALID_COMMAND = "Invalid command";
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("invalidCommand test test", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_badByeCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("byelol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_badListCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("listlol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("No such command."));
    }

    @Test
    public void parse_emptyMarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("mark", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsMarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("mark 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputMarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("mark lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyUnmarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("unmark", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsUnmarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("unmark 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputUnmarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("unmark lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyDeleteCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("delete", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INPUT_TASK_NUMBER));
    }

    @Test
    public void parse_extraInputsDeleteCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("delete 1 1", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_invalidInputDeleteCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("delete lol", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains(INVALID_COMMAND));
    }

    @Test
    public void parse_emptyFindCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("find", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Input something to search for."));
    }

    @Test
    public void parse_emptyTodoCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("todo", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of a todo cannot be empty"));
    }

    @Test
    public void parse_emptyDeadlineCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("deadline", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of a deadline cannot be empty"));
    }

    @Test
    public void parse_emptyEventCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("event", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Description of an event cannot be empty"));
    }

    @Test
    public void parse_emptyUpdateCommand_exceptionThrown() {
        Parser parser = new Parser();
        DukeException thrown = assertThrows(
                DukeException.class, () -> parser.parse("update", false),
                "Expected parse to throw but it didn't.");
        assertTrue(thrown.getMessage().contains("Input the task you would like to update."));
    }
}

package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.TaskCommands.DeadlineCommand;
import duke.commands.TaskCommands.TodoCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

public class ParserTest {

    @Test
    public void testDispatch_todoCommand_success() {
        Parser parser = new Parser();
        String input = "todo read book";
        try {
            Command command = parser.dispatch(input);
            assertTrue(command instanceof TodoCommand);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testDispatch_deadlineCommand_success() {
        Parser parser = new Parser();
        String input = "deadline read book /by 2021-01-01";
        try {
            Command command = parser.dispatch(input);
            assertTrue(command instanceof DeadlineCommand);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testDispatch_unknownCommand_exceptionThrown() {
        Parser parser = new Parser();
        String input = "unknown command";
        assertThrows(UnknownCommandException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_missingDescription_exceptionThrown() {
        Parser parser = new Parser();
        String input = "todo";
        assertThrows(MissingDescriptionException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_incorrectCommandFormat_exceptionThrown() {
        Parser parser = new Parser();
        String input = "deadline";
        assertThrows(MissingDescriptionException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_invalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        String input = "mark 0";
        assertThrows(InvalidIndexException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_deadlineCommand_fail_unknownCommand_exceptionThrown() {
        Parser parser = new Parser();
        String input = "Deadline read book /by 2021-01-01";
        assertThrows(UnknownCommandException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_deadlineCommand_fail_incorrectFormat_exceptionThrown() {
        Parser parser = new Parser();
        String input = "deadline read book 2021-01-01";
        assertThrows(IncorrectCommandFormatException.class, () -> parser.dispatch(input));
    }

    @Test
    public void testDispatch_invalidTimeFormat_exceptionThrown() {
        Parser parser = new Parser();
        String input = "deadline read book /by 2021-13-01";
        assertThrows(InvalidTimeFormatException.class, () -> parser.dispatch(input));
    }
}
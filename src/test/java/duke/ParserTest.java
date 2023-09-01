package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.Parser;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.Command;
import duke.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseListCommand() throws InvalidArgumentException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseDeleteCommand() throws InvalidArgumentException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseMarkCommand() throws InvalidArgumentException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws InvalidArgumentException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void testParseAddCommand() throws InvalidArgumentException {
        Command command = Parser.parse("todo Test");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseExitCommand() throws InvalidArgumentException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parse("invalid_command"));
    }
}

package components;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.MarkAsDoneCommand;
import commands.MarkAsUndoneCommand;
import commands.ShowCommand;
import commands.ToDoCommand;
import commands.UnknownCommand;

public class ParserTest {
    private final Parser parser = new Parser();
    @Test
    public void testParseExitCommand() throws DukeException {
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseShowCommand() throws DukeException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ShowCommand);
    }

    @Test
    public void testParseMarkAsDoneCommand() throws DukeException {
        Command command = parser.parse("mark 1");
        assertTrue(command instanceof MarkAsDoneCommand);
    }

    @Test
    public void testParseMarkAsDoneCommandInvalidIndex() {
        assertThrows(DukeException.class, () -> parser.parse("mark abc"));
    }

    @Test
    public void testParseMarkAsDoneCommandNoIndex() {
        assertThrows(DukeException.class, () -> parser.parse("mark"));
    }

    @Test
    public void testParseMarkAsUndoneCommand() throws DukeException {
        Command command = parser.parse("unmark 1");
        assertTrue(command instanceof MarkAsUndoneCommand);
    }

    @Test
    public void testParseToDoCommand() throws DukeException {
        Command command = parser.parse("todo some task");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws DukeException {
        Command command = parser.parse("deadline some task");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void testParseEventCommand() throws DukeException {
        Command command = parser.parse("event some task");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void testParseDeleteCommand() throws DukeException {
        Command command = parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseUnknownCommand() throws DukeException {
        Command command = parser.parse("unknown_command");
        assertTrue(command instanceof UnknownCommand);
    }
}

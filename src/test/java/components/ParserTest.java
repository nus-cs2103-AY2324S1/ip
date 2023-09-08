package components;

import static org.junit.jupiter.api.Assertions.*;

import commands.*;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseExitCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseShowCommand() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ShowCommand);
    }

    @Test
    public void testParseMarkAsDoneCommand() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkAsDoneCommand);
    }

    @Test
    public void testParseMarkAsDoneCommandInvalidIndex() {
        assertThrows(DukeException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void testParseMarkAsDoneCommandNoIndex() {
        assertThrows(DukeException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void testParseMarkAsUndoneCommand() throws DukeException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkAsUndoneCommand);
    }

    @Test
    public void testParseToDoCommand() throws DukeException {
        Command command = Parser.parse("todo some task");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws DukeException {
        Command command = Parser.parse("deadline some task");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void testParseEventCommand() throws DukeException {
        Command command = Parser.parse("event some task");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void testParseDeleteCommand() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseUnknownCommand() throws DukeException {
        Command command = Parser.parse("unknown_command");
        assertTrue(command instanceof UnknownCommand);
    }
}

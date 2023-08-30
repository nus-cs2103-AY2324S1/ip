package duke;

import duke.command.*;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseCommandListCommand() throws DukeException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseCommandTodoCommand() throws DukeException {
        Command command = Parser.parseCommand("todo sleeping");
        assertTrue(command instanceof TodoCommand);
    }
    @Test
    public void testParseCommandInvalidTodoCommand() throws DukeException {
        try {
            Parser.parseCommand("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseCommandDeadlineCommand() throws DukeException {
        Command command = Parser.parseCommand("deadline Submit CS2103 IP /by 2023-09-01");
        assertTrue(command instanceof DeadlineCommand);

    }

    @Test
    public void testParseCommandInvalidDeadlineCommand() throws DukeException {
        try {
            Parser.parseCommand("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }

    }

    @Test
    public void testParseCommandEventCommand() throws DukeException {
        Command command = Parser.parseCommand("event Party /from 2023-01-01 /to 2023-01-02");
        assertTrue(command instanceof EventCommand);

    }

    @Test
    public void testParseCommandInvalidEventCommand() throws DukeException {
        try {
            Parser.parseCommand("event");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseCommandMarkCommand() throws DukeException {
        Command command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseCommandInvalidMarkCommand() throws DukeException {
        try {
            Parser.parseCommand("unmark a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void testParseCommandUnmarkCommand() throws DukeException {
        Command command = Parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }


    @Test
    public void testParseCommandInvalidUnmarkCommand() throws DukeException {
        try {
            Parser.parseCommand("unmark a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void testParseCommandDeleteCommand() throws DukeException {
        Command command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseCommandInvalidDeleteCommand() throws DukeException {
        try {
            Parser.parseCommand("delete a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }

    }

    @Test
    public void testParseCommandExitCommand() throws DukeException {
        Command command = Parser.parseCommand("exit");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseCommandDoNothingCommand() throws DukeException {
        Command command = Parser.parseCommand("");
        assertTrue(command instanceof DoNothingCommand);
    }

    @Test
    public void testParseCommandInvalidCommand() {
        try {
            Parser.parseCommand("haha");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }



}

package duke;

import duke.command.*;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;

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
        assertEquals(new TodoCommand("sleeping"), command);
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
        assertEquals(new DeadlineCommand("Submit CS2103", LocalDate.parse("2023-09-01")), command);

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
        assertEquals(new EventCommand("Party", LocalDate.parse("2023-01-01"),
                LocalDate.parse("2023-01-02")), command);

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
        assertEquals(new MarkCommand(0), command);

        command = Parser.parseCommand("mark 2");
        assertEquals(new MarkCommand(1), command);

        command = Parser.parseCommand("mark 99");
        assertEquals(new MarkCommand(98), command);
    }

    @Test
    public void testParseCommandInvalidMarkCommand() throws DukeException {
        try {
            Parser.parseCommand("mark a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void testParseCommandUnmarkCommand() throws DukeException {
        Command command = Parser.parseCommand("unmark 1");
        assertEquals(new UnmarkCommand(0), command);

        command = Parser.parseCommand("unmark 2");
        assertEquals(new UnmarkCommand(1), command);

        command = Parser.parseCommand("unmark 6");
        assertEquals(new UnmarkCommand(5), command);
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
        assertEquals(new DeleteCommand(0), command);

        command = Parser.parseCommand("delete 100");
        assertEquals(new DeleteCommand(99), command);
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

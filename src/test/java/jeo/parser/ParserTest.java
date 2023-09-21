package jeo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jeo.command.ByeCommand;
import jeo.command.Command;
import jeo.command.DeleteCommand;
import jeo.command.MarkCommand;
import jeo.command.UnmarkCommand;
import jeo.exception.JeoException;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("blah");
            fail();
        } catch (JeoException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("deadline read");
            fail();
        } catch (JeoException e) {
            assertEquals("OOPS!!! The description of the deadline is of wrong format.", e.getMessage());
        }
    }

    @Test
    public void parse_inputStartsWithBye_returnCorrectCommand() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (JeoException e) {
            fail();
        }
    }

    @Test
    public void parse_inputStartsWithMark_returnCorrectCommand() {
        try {
            Command command = Parser.parse("mark 1");
            assertTrue(command instanceof MarkCommand);
        } catch (JeoException e) {
            fail();
        }
    }

    @Test
    public void parse_inputStartsWithUnmark_returnCorrectCommand() {
        try {
            Command command = Parser.parse("unmark 1");
            assertTrue(command instanceof UnmarkCommand);
        } catch (JeoException e) {
            fail();
        }
    }

    @Test
    public void parse_inputStartsWithDelete_returnCorrectCommand() {
        try {
            Command command = Parser.parse("delete 1");
            assertTrue(command instanceof DeleteCommand);
        } catch (JeoException e) {
            fail();
        }
    }
}

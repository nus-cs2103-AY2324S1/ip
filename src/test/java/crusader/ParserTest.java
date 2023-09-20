package crusader;

import crusader.command.AddTaskCommand;
import crusader.command.DeleteCommand;
import crusader.command.MarkCommand;

import crusader.exception.CrusaderException;
import crusader.exception.CrusaderParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(CrusaderParseException.class, () -> Parser.parse("hello"));
    }

    @Test
    public void parse_todoCommand_success() throws CrusaderException {
        assertTrue(Parser.parse("todo test") instanceof AddTaskCommand);
    }

    @Test
    public void parse_eventCommand_success() throws CrusaderException {
        assertTrue(
                Parser.parse("event test /from 01/01/2023 00 /to 12/12/2023 23") instanceof AddTaskCommand);
    }

    @Test
    public void parse_deadlineCommand_success() throws CrusaderException {
        assertTrue(
                Parser.parse("deadline test /by 12/12/2023 23") instanceof AddTaskCommand);
    }

    @Test
    public void parse_deleteCommand_success() throws CrusaderException {
        assertTrue(
                Parser.parse("delete 1") instanceof DeleteCommand);
    }
    @Test
    public void parse_unmarkCommand_success() throws CrusaderException {
        assertTrue(
                Parser.parse("unmark 2") instanceof MarkCommand);
    }
    @Test
    public void parse_markCommand_success() throws CrusaderException {
        assertTrue(
                Parser.parse("mark 3") instanceof MarkCommand);
    }
    @Test
    public void parse_missingParameterEventCommand_exceptionThrown() {
        assertThrows(
                CrusaderParseException.class,
                () -> Parser.parse("event this is okay /from this is not"));
    }

    @Test
    public void parse_malformedTimeEventCommand_exceptionThrown() {
        assertThrows(
                CrusaderParseException.class,
                () -> Parser.parse("event this is okay /from 01-01-2023 2359 /to 12-12-2023 0000"));
    }

    @Test
    public void parse_badIndexDeleteCommand_exceptionThrown() {
        assertThrows(
                CrusaderParseException.class,
                () -> Parser.parse("delete a"));
    }

    @Test
    public void parse_tooManyParameterMarkCommand_exceptionThrown() {
        assertThrows(
                CrusaderParseException.class,
                () -> Parser.parse("mark 1 2 3 4"));
    }
}

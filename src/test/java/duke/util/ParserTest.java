package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_emptyInput_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Parser.parse("");
        });
    }

    @Test
    public void parse_validByeCommand_exitCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("bye");
        assertTrue(cmd instanceof ExitCommand);
    }

    @Test
    public void parse_validListCommand_listCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }

    @Test
    public void parse_invalidCommand_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Parser.parse("invalidcommand");
        });
    }

    @Test
    public void parse_markCommandWithValidIndex_markCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("mark 2");
        assertTrue(cmd instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommandWithValidIndex_unmarkCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("unmark 2");
        assertTrue(cmd instanceof UnmarkCommand);
    }

    @Test
    public void parse_deleteCommandWithValidIndex_deleteCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("delete 2");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    public void parse_todoCommandWithDescription_addTodoCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("todo sample todo");
        assertTrue(cmd instanceof AddTodoCommand);
    }

    @Test
    public void parse_todoCommandWithoutDescription_emptyTaskExceptionThrown() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("todo ");
        });
    }

    @Test
    public void parse_deadlineCommandWithDescriptionAndBy_addDeadlineCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("deadline sample /by 2023-08-28");
        assertTrue(cmd instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_deadlineCommandWithoutBy_emptyTaskExceptionThrown() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("deadline sample deadline");
        });
    }

    @Test
    public void parse_eventCommandWithDescriptionFromAndTo_addEventCommandReturned() throws DukeException, EmptyTaskException, InvalidDateTimeException {
        Command cmd = Parser.parse("event sample /from 2023-08-28 1200 /to 2023-08-28 1400");
        assertTrue(cmd instanceof AddEventCommand);
    }

    @Test
    public void parse_eventCommandWithoutTo_emptyTaskExceptionThrown() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("event sample /from 2023-08-28 1200");
        });
    }
}

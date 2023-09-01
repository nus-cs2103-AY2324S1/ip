package minion.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import minion.commands.DeadlineCommand;
import minion.commands.DeleteCommand;
import minion.commands.EventCommand;
import minion.commands.MarkCommand;
import minion.commands.ToDoCommand;
import minion.commands.UnmarkCommand;
import minion.common.Messages;
import minion.data.exception.MinionException;
import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.ToDo;

public class CommandParserTest {
    @Test
    public void parse_toDo_success() throws MinionException {
        assertEquals(new ToDoCommand(new ToDo("buy book")), CommandParser.parse("todo buy book"));
    }

    @Test
    public void parse_deadline_success() throws MinionException {
        assertEquals(new DeadlineCommand(new Deadline("return book", "Dec 3 2023 2:00 PM")),
                CommandParser.parse("deadline return book /by 3/12/2023 1400"));
    }

    @Test
    public void parse_deadline_exceptionThrown() {
        // invalid date format
        try {
            assertEquals(null, CommandParser.parse("deadline return book /by 3 Dec 2023 1400"));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_FAIL_PARSE_DATE, e.getMessage());
        }
    }

    @Test
    public void parse_event_success() throws MinionException {
        assertEquals(new EventCommand(new Event("pool party", "Dec 3 2023 2:00 PM", "Dec 4 "
                + "2023 2:30 AM")), CommandParser.parse("event pool party /from 3/12/2023 1400 /to 4/12/2023 "
                        + "0230"));
    }

    @Test
    public void parse_mark_success() throws MinionException {
        // 0 indexed
        assertEquals(new MarkCommand(0), CommandParser.parse("mark 1"));
    }

    @Test
    public void parse_unmark_success() throws MinionException {
        // 0 indexed
        assertEquals(new UnmarkCommand(0), CommandParser.parse("unmark 1"));
    }

    @Test
    public void parse_delete_success() throws MinionException {
        // 0 indexed
        assertEquals(new DeleteCommand(0), CommandParser.parse("delete 1"));
    }

    @Test
    public void parse_emptyCommand_exceptionThrown() {
        // empty string as command
        try {
            assertEquals(null, CommandParser.parse(" "));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_MISSING_COMMAND, e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        // invalid command word
        try {
            assertEquals(null, CommandParser.parse("hello"));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_NO_UNDERSTAND, e.getMessage());
        }
    }
}

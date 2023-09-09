package didier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import didier.command.AddCommand;
import didier.command.Command;
import didier.command.DeleteCommand;
import didier.command.ExitCommand;
import didier.command.FindCommand;
import didier.command.ListCommand;
import didier.command.MarkCommand;
import didier.exception.DidierException;

public class ParserTest {

    @Test
    public void parse_list_success() {
        try {
            Command command = Parser.parseCommand("list");
            assertEquals(command instanceof ListCommand, true);
        } catch (DidierException e) {
            fail();
        }
    }

    @Test
    public void parse_mark_success() {
        try {
            Command command = Parser.parseCommand("mark 2");
            assertEquals(command instanceof MarkCommand, true);
        } catch (DidierException e) {
            fail();
        }
    }

    @Test
    public void parse_delete_success() {
        try {
            Command command = Parser.parseCommand("delete 2");
            assertEquals(command instanceof DeleteCommand, true);
        } catch (DidierException e) {
            fail();
        }
    }

    @Test
    public void parse_event_success() {
        try {
            Command command = Parser.parseCommand("event borrow book \\from 2011-11-11 \\to 2011-11-12");
            assertEquals(command instanceof AddCommand, true);
        } catch (DidierException e) {
            fail();
        }
    }

    @Test
    public void parse_bye_success() {
        try {
            Command command = Parser.parseCommand("bye");
            assertEquals(command instanceof ExitCommand, true);
        } catch (DidierException e) {
            fail();
        }
    }

    @Test
    public void parse_unmark_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("unmark a");
            assertEquals(command instanceof MarkCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("a is an invalid task number. ", e.getMessage());
        }
    }

    @Test
    public void parse_delete_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("delete a");
            assertEquals(command instanceof DeleteCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("a is an invalid task number. ", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("deadline borrow book");
            assertEquals(command instanceof AddCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("The deadline of the task is missing. ", e.getMessage());
        }
    }

    @Test
    public void parse_eventDateMissing_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("event borrow book \\from 2022-12-11  ");
            assertEquals(command instanceof AddCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("The end date of the task is missing. ", e.getMessage());
        }
    }

    @Test
    public void parse_eventDateFormatWrong_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("event borrow book \\from 2022-11-31 \\to 2022-11-20");
            assertEquals(command instanceof AddCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("The date must be in the format yyyy-mm-dd. ", e.getMessage());
        }
    }

    @Test
    public void parse_todo_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("todo    ");
            assertEquals(command instanceof AddCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("The description of the task is missing. ", e.getMessage());
        }
    }

    @Test
    public void parse_find_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("find    ");
            assertEquals(command instanceof FindCommand, false);
            fail();
        } catch (DidierException e) {
            assertEquals("The keyword of the task is missing. ", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Command command = Parser.parseCommand("g");
            assertEquals(command instanceof Command, false);
            fail();
        } catch (DidierException e) {
            assertEquals("I don't quite understand g. ", e.getMessage());
        }
    }
}

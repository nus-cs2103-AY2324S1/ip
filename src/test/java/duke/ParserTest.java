package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.UnmarkDoneCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parser_success() throws DukeException {
        assertTrue(Parser.parse("bye", 4) instanceof ExitCommand);
        assertTrue(Parser.parse("list", 4) instanceof ListCommand);
        assertTrue(Parser.parse("mark 2", 4) instanceof MarkDoneCommand);
        assertTrue(Parser.parse("unmark 2", 4) instanceof UnmarkDoneCommand);
        assertTrue(Parser.parse("delete 2", 4) instanceof DeleteCommand);
        assertTrue(Parser.parse("todo read book", 4) instanceof AddToDoCommand);
        assertTrue(Parser.parse("deadline return book /by 2023-06-06", 4) instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event orientation week /from 2023-07-31 /to 2023-08-04", 4)
                instanceof AddEventCommand);
        assertTrue(Parser.parse("find book", 4) instanceof FindCommand);
    }

    @Test
    public void parser_outOfRange_exceptionThrown() {
        try {
            assertTrue(Parser.parse("blah", 4) instanceof Command);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! I'm sorry, but I don't know what that means :(", e.getMessage());
        }

        try {
            assertTrue(Parser.parse("mark 0", 4) instanceof MarkDoneCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please make sure that the index of the task is within range.",
                    e.getMessage());
        }

        try {
            assertTrue(Parser.parse("unmark 5", 4) instanceof UnmarkDoneCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please make sure that the index of the task is within range.",
                    e.getMessage());
        }

        try {
            assertTrue(Parser.parse("delete 7", 4) instanceof DeleteCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please make sure that the index of the task is within range.",
                    e.getMessage());
        }
    }

    @Test
    public void parser_invalidDate_exceptionThrow() {
        try {
            assertTrue(Parser.parse("deadline return book /by June 6th 2023", 1)
                    instanceof AddDeadlineCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please check that the date is in YYYY-MM-DD format.", e.getMessage());
        }
    }

    @Test
    public void parser_emptyDescriptionOrDate_exceptionThrown() {
        try {
            assertTrue(Parser.parse("todo", 1) instanceof AddToDoCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            assertTrue(Parser.parse("deadline", 1) instanceof AddDeadlineCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please make sure that the description"
                    + " and date of the deadline is not empty.", e.getMessage());
        }

        try {
            assertTrue(Parser.parse("event", 1) instanceof AddEventCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Boop Beep OOPS! Please make sure that the description"
                    + " and dates of the event is not empty.", e.getMessage());
        }
    }
}

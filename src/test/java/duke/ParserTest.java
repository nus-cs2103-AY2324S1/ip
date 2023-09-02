package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void testParseByeCommand() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for 'bye' command.");
        }
    }

    @Test
    public void testParseListCommand() {
        try {
            Command command = Parser.parse("list");
            assertTrue(command instanceof ListCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for 'list' command.");
        }
    }

    @Test
    public void testParseUnknownCommand() {
        try {
            Command command = Parser.parse("invalidcommand");
            fail("Exception should be thrown for unknown command.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void testParseDeadlineCommand() {
        try {
            Command command = Parser.parse("deadline Submit report /by 2023-08-31 23:59");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid 'deadline' command.");
        }
    }

    @Test
    public void testParseEventCommand() {
        try {
            Command command = Parser.parse("event Team meeting /from 2023-09-01 14:00 /to 2023-09-01 15:30");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid 'event' command.");
        }
    }

    @Test
    public void testParseMarkCommand() {
        try {
            Command command = Parser.parse("mark 2");
            assertTrue(command instanceof MarkCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid 'mark' command.");
        }
    }

    @Test
    public void testParseUnmarkCommand() {
        try {
            Command command = Parser.parse("unmark 3");
            assertTrue(command instanceof UnmarkCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid 'unmark' command.");
        }
    }

    @Test
    public void testParseDeleteCommand() {
        try {
            Command command = Parser.parse("delete 1");
            assertTrue(command instanceof DeleteCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid 'delete' command.");
        }
    }

    @Test
    public void testParseInvalidTodoCommand() {
        try {
            // No description provided for todo task
            Parser.parse("todo");
            fail("Exception should be thrown for empty 'todo' description.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseInvalidDeadlineCommand() {
        try {
            // Missing '/by'
            Parser.parse("deadline Submit report 2023-08-31 23:59");
            fail("Exception should be thrown for missing '/by' in 'deadline' command.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! Deadlines must have a /by.", e.getMessage());
        }

        try {
            // Empty '/by'
            Parser.parse("deadline Submit report /by");
            fail("Exception should be thrown for empty '/by' in 'deadline' command.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! Deadlines must have a /by.", e.getMessage());
        }
    }

    @Test
    public void testParseInvalidEventCommand() {
        try {
            // Missing '/from' and '/to'
            Parser.parse("event Team meeting");
            fail("Exception should be thrown for missing '/from' and '/to' in 'event' command.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! Events must have a /from and /to.", e.getMessage());
        }

        try {
            // Empty '/from' or '/to'
            Parser.parse("event Team meeting /from /to 2023-09-01 15:30");
            fail("Exception should be thrown for empty '/from' in 'event' command.");
        } catch (DukeException e) {
            assertEquals(":( OOPS!!! Events must have a /from and /to.", e.getMessage());
        }
    }
}

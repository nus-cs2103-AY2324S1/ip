package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Command.ByeCommand;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.EventCommand;
import duke.Command.FindCommand;
import duke.Command.HelpCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.MassOpsCommand;
import duke.Command.ToDoCommand;
import duke.Command.UnmarkCommand;
import duke.Exception.DukeException;

/**
 * Performs unit tests on the two functions in the Parser.
 */
public class ParserTest {
    /**
     * Tests addToList function in Parser.
     * @throws DukeException duke exception thrown.
     */
    @Test
    void testAddToList() throws DukeException {
        assertTrue(Parser.addToList("todo") instanceof ToDoCommand);
        assertTrue(Parser.addToList("deadline") instanceof DeadlineCommand);
        assertTrue(Parser.addToList("event") instanceof EventCommand);
    }

    /**
     * Tests userCommand function in Parser
     * @throws DukeException duke exception thrown.
     */
    @Test
    void testUserCommand() throws DukeException {
        assertTrue(Parser.addToList("mark") instanceof MarkCommand);
        assertTrue(Parser.addToList("unmark") instanceof UnmarkCommand);
        assertTrue(Parser.addToList("delete") instanceof DeleteCommand);
        assertTrue(Parser.addToList("massDelete") instanceof MassOpsCommand);
        assertTrue(Parser.addToList("list") instanceof ListCommand);
        assertTrue(Parser.addToList("find") instanceof FindCommand);
        assertTrue(Parser.addToList("help") instanceof HelpCommand);
        assertTrue(Parser.addToList("bye") instanceof ByeCommand);
    }
}

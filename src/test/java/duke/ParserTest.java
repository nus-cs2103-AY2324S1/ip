package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DisplayCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.exceptions.DukeException;

public class ParserTest {

    @Test
    public void testParse_validCommands() {
        try {
            Command command = Parser.parse("list");
            assertTrue(command instanceof DisplayCommand);

            command = Parser.parse("mark 2");
            assertTrue(command instanceof MarkCommand);

            command = Parser.parse("unmark 3");
            assertTrue(command instanceof UnMarkCommand);

            command = Parser.parse("todo Buy groceries");
            assertTrue(command instanceof AddToDoCommand);

            command = Parser.parse("deadline Submit report /by 2023-09-01");
            assertTrue(command instanceof AddDeadlineCommand);

            command = Parser.parse("event Team meeting /from 2023-09-05 /to 2023-10-06");
            assertTrue(command instanceof AddEventCommand);

            command = Parser.parse("delete 4");
            assertTrue(command instanceof DeleteCommand);

            command = Parser.parse("find rice");
            assertTrue(command instanceof FindCommand);

            command = Parser.parse("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (DukeException e) {
            fail("Valid command threw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testParse_invalidCommand() {
        assertThrows(DukeException.class, () -> {
            Parser.parse("invalid");
        });
    }
}

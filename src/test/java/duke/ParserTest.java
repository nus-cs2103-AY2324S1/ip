package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents the unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests the parsing of the 'bye' command.
     */
    @Test
    public void byeTest() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof ExitCommand);
        } catch (DukeException e) {
            fail("Exit command should be parsed without errors.");
        }
    }

    /**
     * Tests the parsing of the 'list' command.
     */
    @Test
    public void listTest() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (DukeException e) {
            fail("List command should be parsed without errors.");
        }
    }

    /**
     * Tests the parsing of the 'mark' command.
     */
    @Test
    public void markTest() {
        try {
            Command c = Parser.parse("mark 1");
            assertTrue(c instanceof MarkCommand);
        } catch (DukeException e) {
            fail("Mark command should be parsed without errors.");
        }
    }

    /**
     * Tests the parsing of an invalid command.
     */
    @Test
    public void invalidCommandTest() {
        try {
            Command c = Parser.parse("invalidCommand");
            fail("Invalid command should throw a DukeException.");
        } catch (DukeException e) {
            assertEquals("OOPS! :( ☹ I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

}

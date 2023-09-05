package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_invalidCommand_exceptionThrown() throws DukeException {
        Parser parser = new Parser();
        try {
            parser.parse("abcde");
            fail("This should not be possible! It should have thrown an error.");
        } catch (DukeException e) {
            assertEquals("I'm sorry, I don't know what that means", e.getMessage());
        }
    }

    @Test
    public void parse_listCommand_valid() {
        try {
            Command list = Parser.parse("list");

            assertTrue(list instanceof ListCommand);
        } catch (DukeException e) {
            fail("This should not be possible! It should have worked properly");

        }
    }
}

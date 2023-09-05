package duke.parser;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.ExitCommand;





public class ParserTest {

    @Test
    public void exitCommandParsed_success() throws DukeException {
        assertTrue(Parser.handleInput("bye") instanceof ExitCommand);
    }

}

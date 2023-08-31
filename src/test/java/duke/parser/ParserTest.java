package duke.parser;

import duke.DukeException;
import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void exitCommandParsed_success() throws DukeException {
        assertTrue(Parser.handleInput("bye") instanceof ExitCommand);
    }

}

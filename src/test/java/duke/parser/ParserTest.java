package duke.parser;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_listCommand_success() throws DukeUnknownCommandException {
        Parser parser = new Parser();
        Command listCommand = Command.LIST;
        assertEquals(parser.parseInput("list"), listCommand);
    }

    @Test
    public void parseInput_invalidCommand_errorThrown() throws DukeUnknownCommandException {
        Parser parser = new Parser();
        try {
            parser.parseInput("help");
            fail("Should have thrown invalid command error");
        } catch (DukeException error) {
            return;
        }
    }
}

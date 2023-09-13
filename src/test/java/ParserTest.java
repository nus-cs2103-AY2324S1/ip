import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.command.ExitCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_acceptsUpperCase() {
        String input = "bYe";
        try {
            if (!(Parser.parse(input) instanceof ExitCommand)) {
                fail("bye command not returning ExitCommand");
            }
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        String input = "hello";
        try {
            Parser.parse(input);
            fail(); // Test should not reach this line
        } catch (DukeException e) {
            assertEquals("Unknown Command!", e.getMessage());
        }
    }
}

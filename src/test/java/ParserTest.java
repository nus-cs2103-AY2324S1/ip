import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_acceptsUpperCase() {
        String[] testInputs = {"byE", "List", "mArK", "unMark", "toDO", "unMark", "Todo"};
        String lastInput = testInputs[0];
        try {
            for (String command : testInputs) {
                lastInput = command;
                Parser.parse(command);
            }
        } catch (DukeException e) {
            fail(lastInput + " command failed to parse");
        }
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        String input = "hello";
        try {
            Parser.parse(input);
            fail(); // Test should not reach this line
        } catch (DukeException e) {
            assertEquals(true, true);
        }
    }

    @Test
    public void parse_handleMultipleSpaces() {
        String[] testInputs = {" bye", " bye  ", "    bye"};
        String lastInput = testInputs[0];
        try {
            for (String command : testInputs) {
                lastInput = command;
                Parser.parse(command);
            }
        } catch (DukeException e) {
            fail(lastInput + " command failed to parse");
        }
    }
}

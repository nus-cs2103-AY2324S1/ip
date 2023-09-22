package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class ParserTest {
    @Test
    public void parse_invalidInput_exceptionThrown() throws DukeException {
        try {
            Parser.parse("1234");
        } catch (IllegalArgumentException e) {
            assertEquals("No enum constant duke.Parser.TaskKeyVal.1234", e.getMessage());
        }
    }

    @Test
    public void parse_emptyInput_exceptionThrown() throws DukeException {
        try {
            Parser.parse("");
        } catch (IllegalArgumentException e) {
            assertEquals("No enum constant duke.Parser.TaskKeyVal.", e.getMessage());
        }
    }
    @Test
    public void parse_missingArgument_dukeExceptionThrown() {
        try {
            Parser.parse("delete");
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! The description of a task cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void parse_singleArgumentOperations_success() {
        assertDoesNotThrow(() -> {
            Parser.parse("list");
        });
    }
}

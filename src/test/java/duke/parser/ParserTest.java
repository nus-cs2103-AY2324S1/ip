package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("blah");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("deadline read");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of the deadline is of wrong format.", e.getMessage());
        }
    }
}

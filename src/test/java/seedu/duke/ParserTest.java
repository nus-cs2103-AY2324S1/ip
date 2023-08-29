package seedu.duke;

import duke.DukeException;
import duke.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_wrongCommand_exceptionThrown() {
        try {
            Parser.parse("wrong command");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}

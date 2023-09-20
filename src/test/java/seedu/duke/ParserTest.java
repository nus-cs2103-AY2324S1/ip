package seedu.duke;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;

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

    @Test
    public void deadline_wrongDateFormat_exceptionThrown() {
        try {
            Parser.parse("deadline test /by 2020-13-10");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }

    @Test
    public void event_wrongDateFormat_exceptionThrown() {
        try {
            Parser.parse("event test /from 2020-13-10 /to 2020-14-10");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }
}

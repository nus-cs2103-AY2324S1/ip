package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_emptyCommand_exceptionThrown() {
        try {
            Parser.parse("");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! You have not entered anything!", e.getMessage());
        }

        try {
            Parser.parse("        ");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! You have not entered anything!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("InvalidCommand");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means.", e.getMessage());
        }
    }

    @Test
    public void parse_oneWord_ValidCommand_success() {
        try {
            Parser.parse("bye");
            Parser.parse("list");
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_oneWord_InvalidCommand_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Parser.parse("unmark");
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void parse_multiWordCommand_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("bye bye");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Parser.parse("list the tasks");
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }
}

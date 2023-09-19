package jeo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jeo.command.Command;
import jeo.exception.JeoException;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("blah");
            fail();
        } catch (JeoException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("deadline read");
            fail();
        } catch (JeoException e) {
            assertEquals("OOPS!!! The description of the deadline is of wrong format.", e.getMessage());
        }
    }
}

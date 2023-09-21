package qi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import qi.command.InvalidCommand;
import qi.qiexception.QiException;


public class ParserTest {
    @Test
    public void emptyTodo_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (QiException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty!",
                    e.getMessage());
        }
    }

    @Test
    public void invalidCommand() {
        try {
            assertTrue(Parser.parse("todoeat") instanceof InvalidCommand);
        } catch (QiException e) {
            fail();
        }
    }

    @Test
    public void incompleteDeadline_exceptionThrown() {
        try {
            Parser.parse("deadline return book");
            fail();
        } catch (QiException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty!",
                    e.getMessage());
        }
    }

    @Test
    public void incompleteEvent_exceptionThrown() {
        try {
            Parser.parse("event career fair /from: Monday 2:30");
            fail();
        } catch (QiException e) {
            assertEquals("OOPS!!! The description of an event cannot be empty!",
                    e.getMessage());
        }
    }

    @Test
    public void emptyMark_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (QiException e) {
            assertEquals("OOPS!!! Please specify which task you want to mark!",
                    e.getMessage());
        }
    }
}



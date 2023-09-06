package qi.parser;

import org.junit.jupiter.api.Test;
import qi.qiexception.QiException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void emptyTodo_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (QiException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void emptyMark_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (QiException e) {
            assertEquals("☹ OOPS!!! Please specify which task you want to mark.",
                    e.getMessage());
        }
    }
}
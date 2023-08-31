package duke.test;

import duke.DukeException;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void gibberishTest() throws DukeException {
        try {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", Parser.parse("hehehe"));
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void invalidTodo() throws DukeException {
        try {
            assertEquals("\t ☹ OOPS!!! The description of a todo cannot be empty.", Parser.parse("todo"));
        } catch (DukeException e) {
            assertEquals("\t ☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
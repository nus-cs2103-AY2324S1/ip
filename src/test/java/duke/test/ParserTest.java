package duke.test;

import duke.DukeException;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for the Parser class, which parses user input in Duke.
 */
public class ParserTest {

    /**
     * Test case for gibberish input that should result in an exception.
     *
     * @throws DukeException If there is an issue with parsing.
     */
    @Test
    public void gibberishTest() throws DukeException {
        try {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", Parser.parse("hehehe"));
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    /**
     * Test case for invalid 'todo' input without a description.
     *
     * @throws DukeException If there is an issue with parsing.
     */
    @Test
    public void invalidTodo() throws DukeException {
        try {
            assertEquals("\t ☹ OOPS!!! The description of a todo cannot be empty.", Parser.parse("todo"));
        } catch (DukeException e) {
            assertEquals("\t ☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
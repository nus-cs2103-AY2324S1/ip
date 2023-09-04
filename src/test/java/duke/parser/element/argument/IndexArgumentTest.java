package duke.parser.element.argument;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class IndexArgumentTest {

    @Test
    public void formatInput_string_invalidIndexException() {
        IndexArgument tested = new IndexArgument("");
        try {
            tested.formatInput(" ");
            fail();
        } catch (InvalidIndexException e) {
            return;
        }
    }

    @Test
    public void formatInput_nonpositiveInteger_invalidIndexException() {
        IndexArgument tested = new IndexArgument("");
        try {
            tested.formatInput("0");
            fail();
        } catch (InvalidIndexException e) {
            return;
        }
    }

    @Test
    public void formatInput_positiveInteger_integer() {
        try {
            assertEquals((new IndexArgument("")).formatInput("1"), 1);
        } catch (InvalidIndexException e) {
            fail();
        }
    }

}

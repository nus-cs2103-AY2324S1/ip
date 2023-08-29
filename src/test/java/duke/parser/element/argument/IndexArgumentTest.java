package duke.parser.element.argument;

import duke.exception.InvalidIndexException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class IndexArgumentTest {

    @Test
    public void formatInput_string_invalidIndexException() {
        try {
            (new IndexArgument("")).formatInput(" ");
            fail();
        } catch (InvalidIndexException e) {}
    }

    @Test
    public void formatInput_nonpositiveInteger_invalidIndexException() {
        try {
            (new IndexArgument("")).formatInput("0");
            fail();
        } catch (InvalidIndexException e) {}
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

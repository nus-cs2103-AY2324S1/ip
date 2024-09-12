package duke.parser;

import duke.data.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void parser_getInstruction_success() {
        assertEquals("todo", new Parser().getInstruction("todo this"));
    }

    @Test
    public void parser_getDeadlineDetail_success() throws DukeException {
        assertEquals("this", new Parser().getDeadlineDescription("deadline this /by now"));
    }

    @Test
    public void parser_getFrom_success() throws DukeException {
        assertEquals("now", new Parser().getFrom("event this /from now /to then"));
    }

    @Test
    public void parser_getTaskNumber_success() throws DukeException {
        assertEquals(2, new Parser().getTaskNumber("mark 2"));
    }
}

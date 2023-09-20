package duke;
import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void testParser() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("mark 2") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark 4") instanceof UnmarkCommand);

        assertTrue(Parser.parse("todo CS2103 iP") instanceof AddToDoCommand);
        assertTrue(Parser.parse("deadline ST2334 tutorial /by 2023-09-01 1400") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event Mom's birthday /from 2023-08-17 0000 /to 2023-08-17 2359") instanceof AddEventCommand);
    }
}

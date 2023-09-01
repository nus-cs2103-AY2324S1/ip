package duke;

import duke.command.*;
import duke.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
        @Test
        public void testParse_ValidCommands() {
            // Test valid commands and their corresponding Command classes
            assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
            assertEquals(DeleteCommand.class, Parser.parse("delete 2").getClass());
            assertEquals(ListCommand.class, Parser.parse("list").getClass());
            assertEquals(MarkCommand.class, Parser.parse("mark 1").getClass());
            assertEquals(UnmarkCommand.class, Parser.parse("unmark 3").getClass());
            assertEquals(TodoCommand.class, Parser.parse("todo Buy groceries").getClass());
            assertEquals(DeadlineCommand.class, Parser.parse("deadline Submit report /by 2023-09-10 1900").getClass());
            assertEquals(EventCommand.class, Parser.parse("event Team meeting /from 2023-09-15 1900 /to 2023-09-15 2100").getClass());
        }

        @Test
        public void testParse_InvalidCommand() {
            // Test an invalid command
            assertThrows(InvalidTaskException.class, () -> {
                Parser.parse("invalidCommand");
            });
            assertThrows(InvalidTaskException.class, () -> {
                Parser.parse("");
            });
            assertThrows(InvalidTaskException.class, () -> {
                Parser.parse(" ");
            });
        }
    }
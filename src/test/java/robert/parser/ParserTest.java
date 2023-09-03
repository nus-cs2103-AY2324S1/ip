package robert.parser;

import org.junit.jupiter.api.Test;

import robert.command.AddCommand;
import robert.command.ClearCommand;
import robert.command.DeleteCommand;
import robert.command.ExitCommand;
import robert.command.FilterCommand;
import robert.command.ListCommand;
import robert.command.MarkCommand;
import robert.command.UnmarkCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testReturnedCommandType_nonErrorCommand_success() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
            assertTrue(Parser.parse("todo swim") instanceof AddCommand);
            assertTrue(Parser.parse("event marathon /from 2023-05-11 "
                    + "/to 2023-05-13") instanceof AddCommand);
            assertTrue(Parser.parse("deadline assignment "
                    + "/by 2023-07-29") instanceof AddCommand);
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
            assertTrue(Parser.parse("clear") instanceof ClearCommand);
            assertTrue(Parser.parse("filter 2022-02-02") instanceof FilterCommand);

        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }
}

package duke.parser;

import duke.commands.CommandType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseByeCommand() {
        assertEquals(CommandType.BYE, Parser.parse("bye").getType());
    }

    @Test
    public void testParseListCommand() {
        assertEquals(CommandType.LIST, Parser.parse("list").getType());
    }

    @Test
    public void testParseAddTodoCommand() {
        assertEquals(CommandType.ADD_TODO, Parser.parse("todo Task").getType());
    }

    @Test
    public void testParseAddDeadlineCommand() {
        assertEquals(CommandType.ADD_DEADLINE, Parser.parse("deadline Task /by 01/01/2023 1200").getType());
    }

    @Test
    public void testParseAddEventCommand() {
        assertEquals(CommandType.ADD_EVENT, Parser.parse("event Task /from 01/01/2023 1200 /to 01/01/2023 1400").getType());
    }

    @Test
    public void testParseMarkCommand() {
        assertEquals(CommandType.MARK, Parser.parse("mark 1").getType());
    }

    @Test
    public void testParseUnmarkCommand() {
        assertEquals(CommandType.UNMARK, Parser.parse("unmark 1").getType());
    }

    @Test
    public void testParseDeleteCommand() {
        assertEquals(CommandType.DELETE, Parser.parse("delete 1").getType());
    }

    @Test
    public void testParseInvalidCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("invalid").getType());
    }

    @Test
    public void testParseInvalidTodoCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("todo").getType());
    }

    @Test
    public void testParseInvalidDeadlineCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("deadline Task").getType());
    }

    @Test
    public void testParseInvalidEventCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("event Task /from 01/01/2023 1200").getType());
    }

    @Test
    public void testParseInvalidMarkCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("mark").getType());
    }

    @Test
    public void testParseInvalidDeleteCommand() {
        assertEquals(CommandType.INVALID, Parser.parse("delete").getType());
    }
}

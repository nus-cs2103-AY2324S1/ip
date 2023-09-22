package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.commands.CommandType;

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
        assertEquals(CommandType.ADD_EVENT,
                Parser.parse("event Task /from 01/01/2023 1200 /to 01/01/2023 1400").getType());
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
    public void testParseFindCommand() {
        assertEquals(CommandType.FIND, Parser.parse("find Task").getType());
    }

    @Test
    public void testParseInvalidCommand() {
        // Gibberish user input returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("aklsdjklajd").getType());

        // Missing argument for todo task returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("todo").getType());

        // Missing arguments for deadline task returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("deadline Task").getType());

        // Missing arguments for event task returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("event Task /from 01/01/2023 1200").getType());

        // Missing argument for mark command returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("mark").getType());

        // Missing argument for delete command returns invalid command
        assertEquals(CommandType.INVALID, Parser.parse("delete").getType());
    }

    @Test
    public void addEventCommand_startTimeAfterEndTime_returnsInvalidCommand() {
        assertEquals(CommandType.INVALID,
                Parser.parse("event Task /from 05/03/2023 1400 /to 01/01/2023 1600").getType());
    }
}

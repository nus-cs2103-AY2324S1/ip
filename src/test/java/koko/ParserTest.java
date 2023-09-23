package koko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void testParseCommandType_validCommands() {
        try {
            assertEquals(Command.LIST, parser.parseCommandType("list"));
            assertEquals(Command.MARK, parser.parseCommandType("mark"));
            assertEquals(Command.UNMARK, parser.parseCommandType("unmark"));
            assertEquals(Command.TODO, parser.parseCommandType("todo"));
            assertEquals(Command.DEADLINE, parser.parseCommandType("deadline"));
            assertEquals(Command.EVENT, parser.parseCommandType("event"));
        } catch (DukeException e) {
            fail("Should not throw DukeException");
        }
    }

    @Test
    public void testParseCommandType_invalidCommands() {
        assertThrows(DukeException.class, () -> parser.parseCommandType("invalidCommand"));
        assertThrows(DukeException.class, () -> parser.parseCommandType(""));
        assertThrows(DukeException.class, () -> parser.parseCommandType(null));
    }

    @Test
    public void testParseRemainingArgs_validRemainingArgs() {
        try {
            String remainingArgs = parser.parseRemainingArgs(Command.TODO, "todo sample task");
            assertEquals("sample task", remainingArgs);

            remainingArgs = parser.parseRemainingArgs(Command.MARK, "mark 1");
            assertEquals("1", remainingArgs);

            remainingArgs = parser.parseRemainingArgs(Command.DEADLINE, "deadline sample task /by 2023-08-25");
            assertEquals("sample task /by 2023-08-25", remainingArgs);
        } catch (DukeException e) {
            fail("Should not throw DukeException");
        }
    }

    @Test
    public void testParseRemainingArgs_invalidRemainingArgs() {
        assertThrows(DukeException.class, () -> parser.parseRemainingArgs(Command.TODO, "todo "));
        assertThrows(DukeException.class, () -> parser.parseRemainingArgs(Command.MARK, "mark "));
        assertThrows(DukeException.class, () -> parser.parseRemainingArgs(Command.DEADLINE, "deadline "));
    }
}

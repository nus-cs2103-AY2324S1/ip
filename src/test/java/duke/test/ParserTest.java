package duke.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;



/**
 * Test class for the Parser class, which parses user input in Duke.
 */
public class ParserTest {

    @Test
    public void testParseValidCommands() {
        // Test valid commands
        assertDoesNotThrow(() -> Parser.parse("bye"));
        assertDoesNotThrow(() -> Parser.parse("list"));
        assertDoesNotThrow(() -> Parser.parse("mark 1"));
        assertDoesNotThrow(() -> Parser.parse("unmark 2"));
        assertDoesNotThrow(() -> Parser.parse("todo buy groceries"));
        assertDoesNotThrow(() -> Parser.parse("deadline finish project /by 2023-12-31"));
        assertDoesNotThrow(() -> Parser.parse("event team meeting /from 2023-10-10 /to 2023-10-11"));
        assertDoesNotThrow(() -> Parser.parse("delete 3"));
        assertDoesNotThrow(() -> Parser.parse("find homework"));
    }

    @Test
    public void testParseDeadlineCommand() {
        // Test parsing deadline command
        assertDoesNotThrow(() -> Parser.parse("deadline finish project /by 2023-12-31"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline finish project"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline /by 2023-12-31"));
    }

    @Test
    public void testParseEventCommand() {
        // Test parsing event command
        assertDoesNotThrow(() -> Parser.parse("event team meeting /from 2023-10-10 /to 2023-10-11"));
        assertThrows(DukeException.class, () -> Parser.parse("event team meeting"));
        assertThrows(DukeException.class, () -> Parser.parse("event /from 2023-10-10 /to 2023-10-11"));
        assertThrows(DukeException.class, () -> Parser.parse("event team meeting /from 2023-10-10"));
        assertThrows(DukeException.class, () -> Parser.parse("event team meeting /to 2023-10-11"));
    }

    @Test
    public void testParseDeleteCommand() {
        // Test parsing delete command
        assertDoesNotThrow(() -> Parser.parse("delete 3"));
        assertThrows(DukeException.class, () -> Parser.parse("delete"));
        assertThrows(DukeException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    public void testParseFindCommand() {
        // Test parsing find command
        assertDoesNotThrow(() -> Parser.parse("find homework"));
        assertDoesNotThrow(() -> Parser.parse("find "));
    }

    @Test
    public void testIsValidDateFormat() {
        assertTrue(Parser.isValidDateFormat("2023-12-31"));
        assertFalse(Parser.isValidDateFormat("31-12-2023"));
        assertFalse(Parser.isValidDateFormat("2023/12/31"));
        assertFalse(Parser.isValidDateFormat("2023-12-31 12:00"));
    }

    @Test
    public void testIsValidCommandType() {
        assertTrue(Parser.isValidCommandType("bye"));
        assertTrue(Parser.isValidCommandType("list"));
        assertTrue(Parser.isValidCommandType("mark"));
        assertTrue(Parser.isValidCommandType("unmark"));
        assertTrue(Parser.isValidCommandType("todo"));
        assertTrue(Parser.isValidCommandType("deadline"));
        assertTrue(Parser.isValidCommandType("event"));
        assertTrue(Parser.isValidCommandType("delete"));
        assertTrue(Parser.isValidCommandType("find"));
        assertFalse(Parser.isValidCommandType("invalid"));
    }
}

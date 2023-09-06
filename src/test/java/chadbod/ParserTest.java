package chadbod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_list_success() throws Exception {
        ParsedCommand expected = new ParsedCommand(Command.LIST, "");
        ParsedCommand actual = Parser.parseCommand("list");
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getCommand(), actual.getCommand());
    }
    @Test
    public void parseCommand_mark_success() throws Exception {
        ParsedCommand expected = new ParsedCommand(Command.MARK, "0");
        ParsedCommand actual = Parser.parseCommand("mark 0");
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getCommand(), actual.getCommand());
    }
    @Test
    public void parseCommand_deadlineTask_success() throws Exception {
        ParsedCommand expected = new ParsedCommand(Command.DEADLINE,
                "confess /by 2023-08-28T02:00:00");
        ParsedCommand actual = Parser.parseCommand("deadline confess /by 2023-08-28T02:00:00");
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getCommand(), actual.getCommand());
    }
    @Test
    public void parseCommand_deleteTask_success() throws Exception {
        ParsedCommand expected = new ParsedCommand(Command.DELETE,
                "10");
        ParsedCommand actual = Parser.parseCommand("delete 10");
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getCommand(), actual.getCommand());
    }
    @Test
    public void parseCommand_invalidTask_exceptionThrown() {
        // Tests for exception thrown when command has typo
        try {
            Parser.parseCommand("byee");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
        // Tests for exception thrown when empty command is input
        try {
            Parser.parseCommand("");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}

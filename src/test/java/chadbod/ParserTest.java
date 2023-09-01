package chadbod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_todoTask_success() throws Exception {
        ParsedCommand expected = new ParsedCommand(Command.TODO, "cry");
        ParsedCommand actual = Parser.parseCommand("todo cry");
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getCommand(), actual.getCommand());
    }

    @Test
    public void parseCommand_invalidTask_exceptionThrown() {
        try {
            Parser.parseCommand("hello bye");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
package potato;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import potato.command.Command;
import potato.command.ExitCommand;

public class ParserTest {
    @Test
    public void testParseExitCommand() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (Exception e) {
            System.out.println("Not an Exit Command");
        }
    }
}

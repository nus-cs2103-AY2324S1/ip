package oscar.essential;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import oscar.command.Command;
import oscar.command.ExitCommand;
import oscar.command.ListCommand;
import oscar.exception.OscarException;

public class ParserTest {
    @Test
    public void parse_exitCommand_success() throws OscarException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_listCommand_success() throws OscarException {
        Command command = Parser.parse("LisT");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("abcdef");
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! Oscar does not recognise this command\n", e.getMessage());
        }
    }
}

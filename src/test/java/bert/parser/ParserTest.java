package bert.parser;

import bert.commands.Command;
import bert.commands.ExitCommand;
import bert.commands.ListCommand;
import bert.exceptions.BertEmptyTaskException;
import bert.exceptions.BertException;
import bert.exceptions.BertInvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_emptyInput_exceptionThrown() {
        assertThrows(BertInvalidCommandException.class,
                () -> {
                    new Parser().parse("");
                });
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(BertInvalidCommandException.class,
                () -> {
                    new Parser().parse("asdf");
                });
    }

    @Test
    public void parse_addCommandEmptyArgs_exceptionThrown() {
        assertThrows(BertEmptyTaskException.class,
                () -> {
                    new Parser().parse("todo");
                });
        assertThrows(BertEmptyTaskException.class,
                () -> {
                    new Parser().parse("deadline");
                });
        assertThrows(BertEmptyTaskException.class,
                () -> {
                    new Parser().parse("event");
                });
    }

    @Test
    public void parse_listCommand_success() {
        try {
            Command result = new Parser().parse("list");
            assertTrue(result.getClass().isAssignableFrom(ListCommand.class));
        } catch (BertException e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommand_success() {
        try {
            Command result = new Parser().parse("bye");
            assertTrue(result.getClass().isAssignableFrom(ExitCommand.class));
        } catch (BertException e) {
            fail();
        }
    }
}

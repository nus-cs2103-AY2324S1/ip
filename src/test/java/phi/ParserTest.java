package phi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void handleInvalidInput() {
        String expected = "SIKE I can't process that! Try again or say \"help\" to see a list of all available commands";
        assertEquals(expected, new Parser(new TaskList()).handle("invalid"));
    }

    @Test
    public void helpInput() {
        String expected = "Here's a list of commands:\n"
                + "1. list [prints out a list of all tasks]\n"
                + "2. mark X [marks task X in the list]\n"
                + "3. unmark X [unmarks task X in the list]\n"
                + "4. todo test [creates a to-do with body \"test\"]\n"
                + "5. deadline test /by yyyy-MM-dd [creates a corresponding deadline with body \"test\"]\n"
                + "6. event test /from yyyy-MM-dd /to yyyy-MM-dd [creates a corresponding event with body \"test\"]\n"
                + "7. bye [exits the program]";
        assertEquals(expected, new Parser(new TaskList()).handle("help"));
    }
}

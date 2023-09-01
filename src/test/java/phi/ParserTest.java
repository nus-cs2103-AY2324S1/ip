package phi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void handleInvalidInput() {
        String expected = "SIKE I can't process that! Try again or say \"bye\" to exit";
        assertEquals(expected, new Parser(new TaskList()).handle("invalid"));
    }

    @Test
    public void helpInput() {
        String expected = "Here's a list of commands:\n" +
                "list [prints out a list of all tasks]\n" +
                "mark X [marks task X in the list]\n" +
                "unmark X [unmarks task X in the list]\n" +
                "todo test [creates a to-do with body \"test\"]\n" +
                "deadline test \\by yyyy-MM-dd [creates a corresponding deadline with body \"test\"]\n" +
                "event test \\from yyyy-MM-dd \\to yyyy-MM-dd [creates a corresponding event with body \"test\"]\n" +
                "bye [exits the program]";
        assertEquals(expected, new Parser(new TaskList()).handle("help"));
    }
}

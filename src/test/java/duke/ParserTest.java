package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {

    @Test
    public void command_singleWordCommand_parsedCorrectly() {
        Parser parser = new Parser("bye");
        String result = parser.command();
        assertEquals("bye", result);
    }

    @Test
    public void commandOnly_singleWordCommand_returnsTrue() {
        Parser parser = new Parser("bye");
        assertTrue(parser.commandOnly());
    }

    @Test
    public void commandOnly_multiWordCommand_returnsFalse() {
        Parser parser = new Parser("todo read book");
        assertFalse(parser.commandOnly());
    }

}



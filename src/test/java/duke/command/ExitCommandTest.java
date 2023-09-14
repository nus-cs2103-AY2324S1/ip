package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void testGetCommandType() {
        ExitCommand e = new ExitCommand();
        assertEquals(e.getCommandType(), "Exit");
    }

    @Test
    public void testIsExit() {
        ExitCommand e = new ExitCommand();
        assertTrue(e.isExit());
    }
}

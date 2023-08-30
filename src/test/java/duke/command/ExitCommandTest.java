package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

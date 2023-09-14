package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class UnmarkCommandTest {
    @Test
    public void testGetCommandType() {
        UnmarkCommand um = new UnmarkCommand(13);
        assertEquals(um.getCommandType(), "Unmark");
    }

    @Test
    public void testIsExit() {
        UnmarkCommand um = new UnmarkCommand(13);
        assertFalse(um.isExit());
    }
}

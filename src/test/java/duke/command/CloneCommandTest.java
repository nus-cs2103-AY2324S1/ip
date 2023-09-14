package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class CloneCommandTest {
    @Test
    public void testGetCommandType() {
        CloneCommand c = new CloneCommand(13);
        assertEquals(c.getCommandType(), "Clone");
    }

    @Test
    public void testIsExit() {
        CloneCommand c = new CloneCommand(13);
        assertFalse(c.isExit());
    }
}

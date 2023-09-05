package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class MarkCommandTest {
    @Test
    public void testGetCommandType() {
        MarkCommand m = new MarkCommand(13);
        assertEquals(m.getCommandType(), "Mark");
    }

    @Test
    public void testIsExit() {
        MarkCommand m = new MarkCommand(13);
        assertFalse(m.isExit());
    }
}

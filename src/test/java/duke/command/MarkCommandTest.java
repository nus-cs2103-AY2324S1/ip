package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

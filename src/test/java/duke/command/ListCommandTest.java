package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void testGetCommandType() {
        ListCommand l = new ListCommand();
        assertEquals(l.getCommandType(), "List");
    }

    @Test
    public void testIsExit() {
        ListCommand l = new ListCommand();
        assertFalse(l.isExit());
    }
}

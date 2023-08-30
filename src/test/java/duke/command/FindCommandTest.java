package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {
    @Test
    public void testGetCommandType() {
        FindCommand f = new FindCommand("something");
        assertEquals(f.getCommandType(), "Find");
    }

    @Test
    public void testIsExit() {
        FindCommand f = new FindCommand("something");
        assertFalse(f.isExit());
    }
}

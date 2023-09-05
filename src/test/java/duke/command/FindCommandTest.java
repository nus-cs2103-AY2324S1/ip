package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

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

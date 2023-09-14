package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    @Test
    public void testGetCommandType() {
        DeleteCommand d = new DeleteCommand(13);
        assertEquals(d.getCommandType(), "Delete");
    }

    @Test
    public void testIsExit() {
        DeleteCommand d = new DeleteCommand(13);
        assertFalse(d.isExit());
    }
}

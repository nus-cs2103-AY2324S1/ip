package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

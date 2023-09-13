package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.task.UpdateType;

public class UpdateCommandTest {

    @Test
    public void getCommandType_description_success() {
        UpdateCommand u = new UpdateCommand(4, UpdateType.DESCRIPTION, "something");
        assertEquals("Update DESCRIPTION", u.getCommandType());
    }

    @Test
    public void getCommandType_date1_success() {
        UpdateCommand u = new UpdateCommand(5, UpdateType.DATE1, "2023-10-10T12:34:56");
        assertEquals("Update DATE1", u.getCommandType());
    }

    @Test
    public void getCommandType_date2_success() {
        UpdateCommand u = new UpdateCommand(3, UpdateType.DATE2, "2023-10-10T12:34:57");
        assertEquals("Update DATE2", u.getCommandType());
    }

    @Test
    public void testIsExit() {
        UpdateCommand a = new UpdateCommand(4, UpdateType.DESCRIPTION, "something");
        UpdateCommand b = new UpdateCommand(5, UpdateType.DATE1, "2023-10-10T12:34:56");
        UpdateCommand c = new UpdateCommand(3, UpdateType.DATE2, "2023-10-10T12:34:57");
        assertFalse(a.isExit());
        assertFalse(b.isExit());
        assertFalse(c.isExit());
    }
}

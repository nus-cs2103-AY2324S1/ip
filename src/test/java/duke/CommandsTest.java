package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandsTest {

    @Test
    public void get_listCmd_returnslist() {
        Commands cmd = Commands.get("list");
        assertEquals(cmd, Commands.list);
    }

    @Test
    public void get_markCmd_returnsMark() {
        Commands cmd = Commands.get("mark");
        assertEquals(cmd, Commands.mark);
    }

    @Test
    public void get_unmarkCmd_returnsUnmark() {
        Commands cmd = Commands.get("unmark");
        assertEquals(cmd, Commands.unmark);
    }

    @Test
    public void get_byeCmd_returnsBye() {
        Commands cmd = Commands.get("bye");
        assertEquals(cmd, Commands.bye);
    }

    @Test
    public void get_deleteCmd_returnsDelete() {
        Commands cmd = Commands.get("delete");
        assertEquals(cmd, Commands.delete);
    }

    @Test
    public void get_unknownCmd_returnsUnknown() {
        Commands cmd = Commands.get("");
        assertEquals(cmd, Commands.unknown);
    }
}

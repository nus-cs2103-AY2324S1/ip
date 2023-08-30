package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    @Test
    public void getCommandType_todo_Success() {
        AddCommand a = new AddCommand("Hello");
        assertEquals("Add Todo", a.getCommandType());
    }

    @Test
    public void getCommandType_deadline_Success() {
        AddCommand a = new AddCommand("Hello", "2023-10-10T12:34:56");
        assertEquals("Add Deadline", a.getCommandType());
    }

    @Test
    public void getCommandType_event_Success() {
        AddCommand a = new AddCommand("Hello",
                "2023-10-10T12:34:56", "2023-10-10T12:34:57");
        assertEquals("Add Event", a.getCommandType());
    }

    @Test
    public void testIsExit() {
        AddCommand a = new AddCommand("Hello");
        AddCommand b = new AddCommand("Hello", "2023-10-10T12:34:56");
        AddCommand c = new AddCommand("Hello",
                "2023-10-10T12:34:56", "2023-10-10T12:34:57");
        assertFalse(a.isExit());
        assertFalse(b.isExit());
        assertFalse(c.isExit());
    }
}

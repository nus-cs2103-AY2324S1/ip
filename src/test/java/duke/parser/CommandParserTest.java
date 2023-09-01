package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import duke.commands.Command;

public class CommandParserTest {

    @Test
    public void todoParseTest() {
        CommandParser cp = new CommandParser();
        String testInput = "todo test123 test456";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals(testInput, cmd.toString());
    }

    @Test
    public void deadlineParseTest() {
        CommandParser cp = new CommandParser();
        String testInput = "deadline test123 test456 /by 3/8/2023 1200";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals(testInput, cmd.toString());
    }

    @Test
    public void eventParseTest() {
        CommandParser cp = new CommandParser();
        String testInput = "event test123 test456 /from 3/8/2023 1200 /to 5/8/2023 1200";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals(testInput, cmd.toString());
    }

    @Test
    public void invalidParseTest() {
        CommandParser cp = new CommandParser();
        String testInput = "test123 test456";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals("Invalid Command! Command not found", cmd.toString());
    }

    @Test
    public void invalidParseTest2() {
        CommandParser cp = new CommandParser();
        String testInput = "todo";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals("Invalid Command! Name cannot be empty!", cmd.toString());
    }

    @Test
    public void complexParseTest() {
        CommandParser cp = new CommandParser();
        String testInput = "todo deadline event /from 8/2/2023 1200 /to 8/3/2023 1400 /to 6/5/2023";
        Command cmd = cp.parseCommand(testInput);
        assertNotNull(cmd);
        assertEquals(testInput, cmd.toString());
    }

}

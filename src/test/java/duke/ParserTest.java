package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;


public class ParserTest {
    @Test
    public void testParseCommandForExit() {
        Parser parser = new Parser();
        Command c = null;
        try {
            c = parser.parseCommand("bye");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(c.getClass(), new ExitCommand().getClass());
    }

    @Test
    public void testParseCommandForList() {
        Parser parser = new Parser();
        Command c = null;
        try {
            c = parser.parseCommand("list");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(c.getClass(), new ListCommand().getClass());
    }
}

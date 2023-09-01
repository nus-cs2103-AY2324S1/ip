package duke.components;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.ModifyCommand;

import java.io.IOException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseExitCommand() throws DukeException, IOException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseListCommand() throws DukeException, IOException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ModifyCommand);
        assertEquals("L", ((ModifyCommand) command).getType());
        assertEquals(-1, ((ModifyCommand) command).getIndex());
    }

    @Test
    public void testParseMarkCommand() throws DukeException, IOException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof ModifyCommand);
        assertEquals("M", ((ModifyCommand) command).getType());
        assertEquals(1, ((ModifyCommand) command).getIndex());
    }

    @Test
    public void testParseEventCommand() throws DukeException, IOException {
        Command command = Parser.parse("event cook dinner /from 1/9/2023 1430 /to 1/9/2023 1630");
        assertTrue(command instanceof AddCommand);
        assertEquals("E", ((AddCommand) command).getType());
        assertEquals("cook dinner", ((AddCommand) command).getTask());
        assertEquals(LocalDateTime.of(2023, 9, 1, 14, 30),
                ((AddCommand) command).getFrom());
        assertEquals(LocalDateTime.of(2023, 9, 1, 16, 30),
                ((AddCommand) command).getTo());
    }

    @Test
    public void testParseFilterCommand() throws DukeException, IOException {
        Command command = Parser.parse("find clean");
        assertTrue(command instanceof FilterCommand);
        assertEquals("F", ((FilterCommand) command).getType());
        assertEquals("clean", ((FilterCommand) command).getKeyword());
    }

    @Test
    public void testParseInvalidCommand() throws DukeException, IOException {
        assertThrows(DukeException.class, () -> {
            Parser.parse("invalidcommand");
        });
    }

    @Test
    public void testParseDateTimeWithValidInput() throws DukeException {
        LocalDateTime dateTime = Parser.parseDateTime("1/9/2023 1430");
        assertEquals(LocalDateTime.of(2023, 9, 1, 14, 30), dateTime);
    }

    @Test
    public void testParseDateTimeWithInvalidInput() {
        assertThrows(DukeException.class, () -> {
            Parser.parseDateTime("invalidDateTime");
        });
    }
}

package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.DisplayTaskCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.data.exception.DukeException;

public class ParserTest {

    /** Tests list feature of Parser */
    @Test
    public void parseList() throws DukeException, ParseException {
        Command command = Parser.parseCommand("list");
        assertEquals(command instanceof DisplayTaskCommand, true);
    }

    /** Tests todo feature of Parser */
    @Test
    public void parseAddTodo() throws DukeException, ParseException {
        Command command = Parser.parseCommand("todo go running");
        assertEquals(command instanceof AddToDoCommand, true);
    }

    /** Tests deadline feature of Parser */
    @Test
    public void parseAddDeadline() throws DukeException, ParseException {
        Command command = Parser.parseCommand("deadline do homework /by 2023-12-12");
        assertEquals(command instanceof AddDeadlineCommand, true);
    }

    /** Tests event feature of Parser */
    @Test
    public void parseAddEvent() throws DukeException, ParseException {
        Command command = Parser.parseCommand("event food festival /from 2023-11-11 /to 2023-12-11");
        assertEquals(command instanceof AddEventCommand, true);
    }

    /** Tests delete feature of Parser */
    @Test
    public void parseDelete() throws DukeException, ParseException {
        Command command = Parser.parseCommand("delete 1");
        assertEquals(command instanceof DeleteTaskCommand, true);
    }

    /** Tests mark feature of Parser */
    @Test
    public void parseMark() throws DukeException, ParseException {
        Command command = Parser.parseCommand("mark 1");
        assertEquals(command instanceof MarkCommand, true);
    }

    /** Tests unmark feature of Parser */
    @Test
    public void parseUnmark() throws DukeException, ParseException {
        Command command = Parser.parseCommand("unmark 1");
        assertEquals(command instanceof UnmarkCommand, true);
    }

    /** Tests exception thrown by Parser */
    @Test
    public void parseExceptions() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("blah"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand(" "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline abc"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline /abc"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("event abc"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("event /from abc"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("event /from abc /to abc"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("eventdeadline"));
    }
}

package duke.main;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseByeCommandTest() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseListCommandTest() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseToDoCommandTest() throws DukeException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void parseDeadlineCommandTest() throws DukeException {
        Command command = Parser.parse("deadline Submit report /by 2023-09-15");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parseEventCommandTest() throws DukeException {
        Command command = Parser.parse("event Party /from 2023-09-20 1400 /to 2023-09-20 1800");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parseMarkCommandTest() throws DukeException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseUnmarkCommandTest() throws DukeException {
        Command command = Parser.parse("unmark 3");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parseDeleteCommandTest() throws DukeException {
        Command command = Parser.parse("delete 4");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseInvalidCommandTest() {
        assertThrows(DukeException.class, () -> Parser.parse("invalid command"));
    }
}

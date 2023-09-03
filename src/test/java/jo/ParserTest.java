package jo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jo.command.AddCommand;
import jo.command.CheckCommand;
import jo.command.Command;
import jo.command.DeleteCommand;
import jo.command.ExitCommand;
import jo.command.ListCommand;
import jo.command.MarkCommand;
import jo.task.Deadline;
import jo.task.Event;
import jo.task.Task;

public class ParserTest {
    @Test
    public void parseExitCommand() throws JoException {
        ExitCommand expectedExitCommand = new ExitCommand();
        Command parsedCommand = Parser.parse("bye");
        assertEquals(expectedExitCommand.getClass(), parsedCommand.getClass());
    }
    @Test
    public void parseDeleteCommand() throws JoException {
        DeleteCommand expectedDeleteCommand = new DeleteCommand(1);
        Command parsedDeleteCommand = Parser.parse("delete 1");
        assertEquals(expectedDeleteCommand.getClass(), parsedDeleteCommand.getClass());
    }
    @Test
    public void parseCheckCommand() throws JoException {
        LocalDate deadline = LocalDate.parse("2023-09-09");
        CheckCommand expectedCheckCommand = new CheckCommand(deadline);
        Command parsedCheckCommand = Parser.parse("check 2023-09-09");
        assertEquals(expectedCheckCommand.getClass(), parsedCheckCommand.getClass());
    }
    @Test
    public void parseMarkCommand() throws JoException {
        MarkCommand expectedMarkCommand = new MarkCommand(1, true);
        Command parsedMarkCommand = Parser.parse("mark 1");
        assertEquals(expectedMarkCommand.getClass(), parsedMarkCommand.getClass());

        MarkCommand expectedMarkCommand2 = new MarkCommand(1, false);
        Command parsedUnmarkCommand = Parser.parse("unmark 1");
        assertEquals(expectedMarkCommand2.getClass(), parsedUnmarkCommand.getClass());
    }
    @Test
    public void parseListCommand() throws JoException {
        ListCommand expectedListCommand = new ListCommand();
        Command parsedListCommand = Parser.parse("list");
        assertEquals(expectedListCommand.getClass(), parsedListCommand.getClass());
    }
    @Test
    public void parseTodoCommand() throws JoException {
        String input = "todo read book";
        Command command = Parser.parse(input);
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof Task);
        assertEquals("read book", addCommand.getTask().getDescription());
    }
    @Test
    public void parseDeadlineCommand() throws JoException {
        String input = "deadline assignment /by 2023-12-31";
        Command command = Parser.parse(input);
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof Deadline);
        Deadline task = (Deadline) addCommand.getTask();
        assertEquals("assignment", task.getDescription());
        assertFalse(task.getIsDone());
        assertEquals("2023-12-31", task.getDeadline().toString());
    }
    @Test
    public void parseEventCommand() throws JoException {
        String input = "event party /from 2023-12-31 /to 2024-01-01";
        Command command = Parser.parse(input);
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof Event);
        Event event = (Event) addCommand.getTask();
        assertEquals("party", event.getDescription());
        assertFalse(event.getIsDone());
        assertEquals("2024-01-01", event.getDeadline().toString());
    }
    @Test
    public void parseEmptyInput() {
        String input = "";
        assertThrows(JoException.class, () -> Parser.parse(input));
    }

}

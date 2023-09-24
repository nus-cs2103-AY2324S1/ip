package ekud.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;

import ekud.error.ParseException;

public final class ParserTest {
    @Test
    public void parse_listCommand() {
        Command command = Parser.parseCommand("list");
        assertEquals(command instanceof ListCommand, true);
    }

    @Test
    public void parse_markCommand() {
        Command command = Parser.parseCommand("mark 23");
        assertEquals(command instanceof MarkCommand, true);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(markCommand.getTaskId(), 23);
    }

    @Test
    public void parse_unmarkCommand() {
        Command command = Parser.parseCommand("unmark 23");
        assertEquals(command instanceof UnmarkCommand, true);
        UnmarkCommand markCommand = (UnmarkCommand) command;
        assertEquals(markCommand.getTaskId(), 23);
    }

    @Test
    public void parse_deleteCommand() {
        Command command = Parser.parseCommand("delete 23");
        assertEquals(command instanceof DeleteCommand, true);
        DeleteCommand markCommand = (DeleteCommand) command;
        assertEquals(markCommand.getTaskId(), 23);
    }

    @Test
    public void parse_createTodoCommand() {
        Command command = Parser.parseCommand("todo this is a test");
        assertEquals(command instanceof CreateTodoCommand, true);
        CreateTodoCommand createTodoCommand = (CreateTodoCommand) command;
        assertEquals(createTodoCommand.getTitle(), "this is a test");
    }

    @Test
    public void parse_createDeadlineCommand() {
        Command command = Parser.parseCommand("deadline this is a test /by 18:32");
        assertEquals(command instanceof CreateDeadlineCommand, true);
        CreateDeadlineCommand createDeadlineCommand = (CreateDeadlineCommand) command;
        assertEquals(createDeadlineCommand.getTitle(), "this is a test");
        assertEquals(createDeadlineCommand.getBy().getTime().format(DateTimeFormatter.ofPattern("HH:mm")), "18:32");
    }

    @Test
    public void parse_createEventCommand() {
        Command command = Parser.parseCommand("event this is a test /from 03-04-2023 /to 04-04-2023");
        assertEquals(command instanceof CreateEventCommand, true);
        CreateEventCommand createEventCommand = (CreateEventCommand) command;
        assertEquals(createEventCommand.getTitle(), "this is a test");
        assertEquals(createEventCommand.getFrom().getDate().format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                "3 Apr 2023");
        assertEquals(createEventCommand.getTo().getDate().format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                "4 Apr 2023");
    }

    @Test
    public void parse_invalidCommand() {
        assertThrows(ParseException.class, () -> Parser.parseCommand("ls"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("harblah"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("todo"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("deadline this is a test"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("mark wrong"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("unmark wrong"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("delete wrong"));
        assertThrows(ParseException.class, () -> Parser.parseCommand("deadlin this is a test /by 07-03-2023"));
        assertThrows(ParseException.class,
                () -> Parser.parseCommand("evnt this is a test /from 07-03-2023 /to 08-03-2023"));
    }
}

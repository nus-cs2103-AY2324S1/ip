package sana;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_todoCommand_addCommandReturned() {
        Command actual = Parser.parse("todo read book");
        Command expected = new AddCommand("todo", "read book");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_eventCommand_addCommandReturned() {
        Command actual = Parser.parse("event return book /from 2023-12-30 /to 2023-12-31");
        Command expected = new AddCommand("event", "return book /from 2023-12-30 /to 2023-12-31");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineCommand_addCommandReturned() {
        Command actual = Parser.parse("deadline buy book /from 2023-12-30");
        Command expected = new AddCommand("deadline", "buy book /from 2023-12-30");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_byeCommand_exitCommandReturned() {
        Command actual = Parser.parse("bye");
        Command expected = new ExitCommand("bye", " ");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_listCommand_listCommandReturned() {
        Command actual = Parser.parse("list");
        Command expected = new ListCommand("list", " ");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deleteCommand_deleteCommandReturned() {
        Command actual = Parser.parse("delete 1");
        Command expected = new ExitCommand("delete", "1");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_markCommand_markCommandReturned() {
        Command actual = Parser.parse("mark 1");
        Command expected = new MarkCommand("mark", "1");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_unmarkCommand_unmarkCommandReturned() {
        Command actual = Parser.parse("unmark 1");
        Command expected = new UnmarkCommand("unmark", "1");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_invalidCommand_helpCommandReturned() {
        Command actual = Parser.parse("invalid command");
        Command expected = new HelpCommand("invalid", "command");
        assertEquals(expected, actual);
    }
}

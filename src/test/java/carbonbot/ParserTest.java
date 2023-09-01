package carbonbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import carbonbot.command.AddCommand;
import carbonbot.command.Command;
import carbonbot.command.DeleteCommand;
import carbonbot.command.ExitCommand;
import carbonbot.command.ListCommand;


public class ParserTest {
    @Test
    public void testParseExit() throws DukeException {
        String fullCommand = "bye";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void testParseList() throws DukeException {
        String fullCommand = "list";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void testParseTodoAdd() throws DukeException {
        String fullCommand = "todo sleep";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void testParseDeadlineAdd() throws DukeException {
        String fullCommand = "deadline lab1 assignment /by 2/12/2020 2350";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void testParseEventAdd() throws DukeException {
        String fullCommand = "event cs3333 lecture /from 4/12/2020 2150 /to 4/12/2020 2350";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void testParseDelete() throws DukeException {
        String fullCommand = "delete 2";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void testParseUnknown() throws DukeException {
        String fullCommand = "somerubbishcommand 1 2 3";
        try {
            Command c = Parser.parse(fullCommand);
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                            + "\nMy supported commands are: list, mark, unmark, todo, deadline, event, find, bye.",
                    e.getMessage());
        }

    }
}

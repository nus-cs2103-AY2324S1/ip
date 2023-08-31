package parser;

import commands.*;
import data.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testEmptyCommand() {
        Parser parser = new Parser();
        try {
            Command c = parser.parse("");
            assertTrue(c instanceof EmptyCommand);

        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testUnknownCommand() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("error")
        );
    }

    @Test
    public void testValidCommands() {
        Parser parser = new Parser();
        try {
            Command mark = parser.parse("mark 1");
            Command unmark = parser.parse("unmark 1");
            Command list = parser.parse("list");
            Command delete = parser.parse("delete 1");
            Command todo = parser.parse("todo read");
            Command deadline = parser.parse(
                "deadline read /by 2023-10-20 1800"
            );
            Command event = parser.parse(
                "event read /from 2023-10-20 1800 /to 2023-10-25 1900"
            );
            assertTrue(mark instanceof MarkCommand);
            assertTrue(unmark instanceof MarkCommand);
            assertTrue(list instanceof ListCommand);
            assertTrue(delete instanceof DeleteCommand);
            assertTrue(todo instanceof TodoCommand);
            assertTrue(deadline instanceof DeadlineCommand);
            assertTrue(event instanceof EventCommand);
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testValidDates() {
        Parser parser = new Parser();
        try {
            parser.parse("deadline read /by 2023-10-20");
            parser.parse("deadline read /by 20-10-2023");
            parser.parse("deadline read /by 20/10/2023");
            parser.parse("deadline read /by 2023/10/20");
            parser.parse("deadline read /by Oct 10 2023");
            parser.parse("deadline read /by 20 Oct 2023");
            parser.parse("deadline read /by 2023-10-20 1800");
            parser.parse("deadline read /by 2023-10-20 630");
            parser.parse("deadline read /by 2023-10-20 0630");
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testInvalidMark() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("mark")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("mark task 1")
        );
    }

    @Test
    public void testInvalidDelete() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("delete")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("delete task 1")
        );
    }

    @Test
    public void testInvalidTodo() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("todo")
        );
    }

    @Test
    public void testInvalidDeadline() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("deadline")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("deadline read")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("deadline read /by")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("deadline /by")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("deadline read /by random date")
        );
    }

    @Test
    public void testInvalidEvent() {
        Parser parser = new Parser();
        assertThrows(
            DukeException.class,
            () -> parser.parse("event")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("event read")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("event read /from")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("event read /from hi")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("event read /from hi /to")
        );
        assertThrows(
            DukeException.class,
            () -> parser.parse("event read /from hi /to bye")
        );
    }
}

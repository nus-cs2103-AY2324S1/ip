package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.data.exception.DukeException;


public class ParserTest {
    @Test
    public void parse_emptyCommandWord_throwsDukeException() {
        Parser parser = new Parser();
        assertThrows(DukeException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidCommandWord_throwsDukeException() {
        Parser parser = new Parser();
        assertThrows(DukeException.class, () -> parser.parse("abc"));
    }

    @Test
    public void parse_byeCommandWord_returnsExitCommand() {
        Parser parser = new Parser();

        try {
            assertEquals(parser.parse("bye").getClass(), ExitCommand.class);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyTodoDescription_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("todo");
        } catch (DukeException e) {
            return;
        }
        fail("Add Todo Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_validTodo_returnsAddTodoCommand() {
        Parser parser = new Parser();
        Command command;
        try {
            command = parser.parse("todo desc abc");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddTodoCommand.class);
    }

    @Test
    public void parse_emptyDeadlineDescription_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("deadline");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_emptyDeadlineBy_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("deadline desc");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed without /by field.");
    }

    @Test
    public void parse_invalidDeadlineBy_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("deadline desc /by invalid");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed with invalid /by field.");
    }

    @Test
    public void parse_validDeadline_returnsAddDeadlineCommand() {
        Parser parser = new Parser();
        Command command;
        try {
            command = parser.parse("deadline desc /by 2023-01-01");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddDeadlineCommand.class);
    }

    @Test
    public void parse_emptyEventDescription_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("event");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with empty description.");

    }

    @Test
    public void parse_invalidEventFrom_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("deadline desc /from invalid /to 2023-01-01");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with invalid /from field.");
    }

    @Test
    public void parse_invalidEventTo_throwsDukeException() {
        Parser parser = new Parser();

        try {
            parser.parse("deadline desc /from 2023-01-01 /to invalid");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with invalid /to field.");
    }

    @Test
    public void parse_validEvent_returnsAddEventCommand() {
        Parser parser = new Parser();
        Command command;
        try {
            command = parser.parse("event desc /from 2023-01-01 /to 2024-01-01");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddEventCommand.class);
    }
}

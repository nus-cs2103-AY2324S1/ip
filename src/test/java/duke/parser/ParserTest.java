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
        assertThrows(DukeException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_invalidCommandWord_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("abc"));
    }

    @Test
    public void parse_byeCommandWord_returnsExitCommand() {
        try {
            assertEquals(Parser.parse("bye").getClass(), ExitCommand.class);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyTodoDescription_throwsDukeException() {
        try {
            Parser.parse("todo");
        } catch (DukeException e) {
            return;
        }
        fail("Add Todo Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_validTodo_returnsAddTodoCommand() {
        Command command;
        try {
            command = Parser.parse("todo desc abc");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddTodoCommand.class);
    }

    @Test
    public void parse_emptyDeadlineDescription_throwsDukeException() {
        try {
            Parser.parse("deadline");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_emptyDeadlineBy_throwsDukeException() {
        try {
            Parser.parse("deadline desc");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed without /by field.");
    }

    @Test
    public void parse_invalidDeadlineBy_throwsDukeException() {
        try {
            Parser.parse("deadline desc /by invalid");
        } catch (DukeException e) {
            return;
        }
        fail("Add Deadline Command was successfully constructed with invalid /by field.");
    }

    @Test
    public void parse_validDeadline_returnsAddDeadlineCommand() {
        Command command;
        try {
            command = Parser.parse("deadline desc /by 2023-01-01");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddDeadlineCommand.class);
    }

    @Test
    public void parse_emptyEventDescription_throwsDukeException() {
        try {
            Parser.parse("event");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with empty description.");

    }

    @Test
    public void parse_invalidEventFrom_throwsDukeException() {
        try {
            Parser.parse("deadline desc /from invalid /to 2023-01-01");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with invalid /from field.");
    }

    @Test
    public void parse_invalidEventTo_throwsDukeException() {
        try {
            Parser.parse("deadline desc /from 2023-01-01 /to invalid");
        } catch (DukeException e) {
            return;
        }
        fail("Add Event Command was successfully constructed with invalid /to field.");
    }

    @Test
    public void parse_validEvent_returnsAddEventCommand() {
        Command command;
        try {
            command = Parser.parse("event desc /from 2023-01-01 /to 2024-01-01");
        } catch (DukeException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(command.getClass(), AddEventCommand.class);
    }
}

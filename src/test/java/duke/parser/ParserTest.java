package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ParserTest {

    @Test
    public void parseListCommand_validInput_success() {
        try {
            Command command = Parser.parse("list");
            assertTrue(command instanceof ListCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseByeCommand_validInput_success() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_validInput_success() {
        try {
            Command command = Parser.parse("event Orbital Splashdown /from 01/01/2023 1800 /to 01/01/2023 2000");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_invalidInput_exceptionThrown() {
        try {
            Command command = Parser.parse("event Orbital Splashdown /to 01/01/2023 2000");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The format of an event task is " +
                    "\"event TASK_DESCRIPTION /from START /to END\"", e.getMessage());
        }
    }


    @Test
    public void parseDeadlineCommand_validInput_success() {
        try {
            Command command1 = Parser.parse("deadline Do CS2103T /by 01/01/2023 1800");
            Command command2 = Parser.parse("deadline Do CS2103T /by 1/01/2023 1800");
            assertTrue(command1 instanceof AddCommand);
            assertTrue(command2 instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineCommand_invalidInputFormat_exceptionThrown() {
        try {
            Command command = Parser.parseDeadlineCommand("deadline Do CS2103T 1800");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The format of a deadline task is " +
                    "\"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\"", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineCommand_invalidDateInput_exceptionThrown() {
        try {
            Command command = Parser.parseDeadlineCommand("deadline Do CS2103T /by 25/25/2023 1800");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid date or time format in deadline task.", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineCommand_invalidTimeInput_exceptionThrown() {
        try {
            Command command = Parser.parseDeadlineCommand("deadline Do CS2103T /by 12/12/2023 2500");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid date or time format in deadline task.", e.getMessage());
        }
    }

    @Test
    public void parseToDoCommand_validInput_success() {
        try {
            Command command = Parser.parse("todo read book");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseToDoCommand_invalidInput_exceptionThrown() {
        try {
            Command command = Parser.parse("todo");
            assertTrue(command instanceof AddCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseMarkCommand_validInput_success() {
        try {
            Command command = Parser.parse("mark 1");
            assertTrue(command instanceof MarkCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseMarkCommand_invalidInputFormat_exceptionThrown() {
        try {
            Command command = Parser.parse("mark");
            assertTrue(command instanceof MarkCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The format of marking a task done is \"mark TASK_NUMBER\".\n" +
                    "Task number must exist in the task list.", e.getMessage());
        }
    }

    @Test
    public void parseUnmarkCommand_validInput_success() {
        try {
            Command command = Parser.parse("unmark 1");
            assertTrue(command instanceof UnmarkCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseUnmarkCommand_invalidInputFormat_exceptionThrown() {
        try {
            Command command = Parser.parse("unmark");
            assertTrue(command instanceof UnmarkCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The format of marking a task done is \"unmark TASK_NUMBER\".\n" +
                    "Task number must exist in the task list.", e.getMessage());
        }
    }

    @Test
    public void parseDeleteCommand_validInput_success() {
        try {
            Command command = Parser.parse("delete 1");
            assertTrue(command instanceof DeleteCommand);
        } catch (DukeException e) {
            assertEquals("Exception should not be thrown", e.getMessage());
        }
    }

    @Test
    public void parseDeleteCommand_invalidInputFormat_exceptionThrown() {
        try {
            Command command = Parser.parse("delete 1");
            assertTrue(command instanceof DeleteCommand);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The format of marking a task done is \"delete TASK_NUMBER\".\n" +
                    "Task number must exist in the task list.", e.getMessage());
        }
    }

    @Test
    public void testParseDate_validInput_success() {
        LocalDate result = Parser.parseDate("31/08/2023");
        assertEquals(LocalDate.of(2023, 8, 31), result);
    }

    @Test
    public void testParseDate_invalidMonth_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("31/13/2023"));
    }

    @Test
    public void testParseDate_invalidDay_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("32/08/2023"));
    }

    @Test
    public void testParseTime_validInput_success() {
        LocalTime result = Parser.parseTime("1345");
        assertEquals(LocalTime.of(13, 45), result);
    }

    @Test
    public void testParseTime_validInputWithLeadingZero_success() {
        LocalTime result = Parser.parseTime("0900");
        assertEquals(LocalTime.of(9, 0), result);
    }

    @Test
    public void testParseTime_invalidHour_ExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseTime("2560"));
    }

    @Test
    public void testParseTime_invalidMinute_ExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseTime("13160"));
    }
}

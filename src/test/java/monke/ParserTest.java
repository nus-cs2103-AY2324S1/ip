package monke;

import monke.commands.*;
import org.junit.jupiter.api.Test;

import static monke.Parser.parse;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    // List command tests
    @Test public void parse_correctListCommand_success() throws MonkeException {
        assertTrue(parse("list") instanceof ListCommand);
    }

    // Mark command tests
    @Test
    public void parse_correctMarkCommand_success() throws MonkeException {
        assertTrue(parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_markCommandWithoutListNumber_exceptionThrown() throws MonkeException {
        try {
            assertTrue(parse("mark") instanceof MarkCommand);
            fail();
        } catch(MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    @Test
    public void parse_MarkCommandNotNumber_exceptionThrown() throws MonkeException {
        try {
            assertTrue(parse("mark hello") instanceof MarkCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    // Unmark command tests
    @Test
    public void parse_correctUnmarkCommand_success() throws MonkeException {
        assertTrue(parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkCommandWithoutListNumber_exceptionThrown() throws MonkeException {
        try {
            assertTrue(parse("unmark") instanceof UnmarkCommand);
            fail();
        } catch(MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommandNotNumber_exceptionThrown() throws MonkeException {
        try {
            assertTrue(parse("unmark hello") instanceof UnmarkCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    // Todo command tests
    @Test
    public void parse_correctTodoCommand_success() throws MonkeException {
        assertTrue(parse("todo test") instanceof TodoCommand);
    }

    @Test
    public void parse_emptyTodo_exceptionThrown() {
        try {
            assertTrue(parse("todo") instanceof TodoCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_whitespaceAfterTodo_exceptionThrown() {
        try {
            assertTrue(parse("todo       ") instanceof TodoCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    // Deadline command tests
    @Test
    public void parse_correctDeadlineCommand_success() throws MonkeException {
        assertTrue(parse("deadline do ip /by 2024-09-12 1200") instanceof DeadlineCommand);
    }

    @Test
    public void parse_emptyDeadline_exceptionThrown() {
        try {
            assertTrue(parse("deadline") instanceof DeadlineCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>", e.getMessage());
        }
    }

    @Test public void parse_deadlineWithNoBy_exceptionThrown() {
        try {
            assertTrue(parse("deadline do ip") instanceof DeadlineCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithIncorrectDateFormat_exceptionThrown() {
        try {
            assertTrue(parse("deadline do ip /by monday") instanceof TodoCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("Format your deadline in yyyy-MM-dd HHmm format", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoTask_exceptionThrown() {
        try {
            assertTrue(parse("deadline /by 2024-12-03") instanceof TodoCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>", e.getMessage());
        }
    }

    // Event command tests
    @Test
    public void parse_correctEventCommand_success() throws MonkeException {
        assertTrue(parse("event do ip /from 2pm /to 4pm") instanceof EventCommand);
    }

    @Test
    public void parse_EmptyEvent_exceptionThrown() {
        try {
            assertTrue(parse("event") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithoutTime_exceptionThrown() {
        try {
            assertTrue(parse("event do ip") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithFromButNoTo_exceptionThrown() {
        try {
            assertTrue(parse("event do ip /from 2pm") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithToButNoFrom_exceptionThrown() {
        try {
            assertTrue(parse("event do ip /to 4pm") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventArgumentsInWrongOrder_exceptionThrown() {
        try {
            assertTrue(parse("event do ip /to 4pm /from 2pm") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventNoTask_exceptionThrown() {
        try {
            assertTrue(parse("event /to 4pm /from 2pm") instanceof EventCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>", e.getMessage());
        }
    }

    // Delete command tests
    @Test
    public void parse_correctDeleteCommand_success() throws MonkeException {
        assertTrue(parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteCommandNoNumber_exceptionThrown() {
        try {
            assertTrue(parse("delete") instanceof DeleteCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandNotNumber_exceptionThrown() {
        try {
            assertTrue(parse("delete hello") instanceof DeleteCommand);
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Please provide a list number", e.getMessage());
        }
    }

    // Exit command tests
    @Test
    public void parse_correctExitCommand_success() throws MonkeException {
        assertTrue(parse("bye") instanceof ExitCommand);
    }
}

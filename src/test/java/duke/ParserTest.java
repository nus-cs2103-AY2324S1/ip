package duke;

import command.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseCommand_emptyString_success() throws DukeException {
        assertEquals(new EmptyCommand(), Parser.parseCommand(""));
    }

    @Test
    public void parseCommand_spaces_success() throws DukeException {
        assertEquals(new EmptyCommand(), Parser.parseCommand("    "));
    }

    @Test
    public void parseCommand_byeCommand_success() throws DukeException {
        assertEquals(new ExitCommand(), Parser.parseCommand("bye"));
    }

    @Test
    public void parseCommand_byeCommandWithspace_success() throws DukeException {
        assertEquals(new ExitCommand(), Parser.parseCommand("   bye     "));
    }

    @Test
    public void parseCommand_byeCommandWithArgument_exceptionThrown() {
        try {
            Parser.parseCommand("bye 45");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! The bye command should not be followed by any description"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_listCommand_success() throws DukeException {
        assertEquals(new ListCommand(), Parser.parseCommand("list"));
    }

    @Test
    public void parseCommand_listCommandWithArgument_exceptionThrown() {
        try {
            Parser.parseCommand("list 123");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! The list command should not be followed by any description"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommand_success() throws DukeException {
        assertEquals(new MarkCommand(1), Parser.parseCommand("mark 1"));
    }

    @Test
    public void parseCommand_markCommandWithNonInteger_exceptionThrown() {
        try {
            Parser.parseCommand("mark sh");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of a mark command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommandWithMultipleArguments_exceptionThrown() {
        try {
            Parser.parseCommand("mark 1 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of a mark command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkCommand_success() throws DukeException {
        assertEquals(new UnmarkCommand(1), Parser.parseCommand("unmark 1"));
    }

    @Test
    public void parseCommand_unmarkCommandWithNonInteger_exceptionThrown() {
        try {
            Parser.parseCommand("unmark sh");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of an unmark command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkCommandWithMultipleArguments_exceptionThrown() {
        try {
            Parser.parseCommand("unmark 1 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of an unmark command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_todoCommand_success() throws DukeException {
        assertEquals(new TodoCommand("test"), Parser.parseCommand("todo test"));
    }

    @Test
    public void parseCommand_todoCommandWithoutDescription_exceptionThrown() {
        try {
            Parser.parseCommand("todo   ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! The description of a todo task cannot be empty", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommand_success() throws DukeException {
        assertEquals(new DeadlineCommand("test", LocalDate.of(2023, 8, 8))
                , Parser.parseCommand("deadline test /by 2023-08-08"));
    }

    @Test
    public void parseCommand_deadlineCommandWithoutArguments_exceptionThrown() {
        try {
            Parser.parseCommand("deadline ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! You forgot to provide a deadline for the deadline task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWithEmptyDescription_exceptionThrown() {
        try {
            Parser.parseCommand("deadline  /by 2023-08-08");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! The description of a deadline task cannot be empty", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWithEmptyDeadline_exceptionThrown() {
        try {
            Parser.parseCommand("deadline  test /by ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! You forgot to provide a deadline for the deadline task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWitInvalidDeadline_exceptionThrown() {
        try {
            Parser.parseCommand("deadline  test /by 2023/04/12");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!! the date format of deadline is incorrect" +
                    ", please use the format yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommand_success() throws DukeException {
        assertEquals(new EventCommand("test"
                        , LocalDate.of(2023, 8, 8)
                        , LocalDate.of(2023, 9, 9))
                , Parser.parseCommand("event test /from 2023-08-08 /to 2023-09-09"));
    }

    @Test
    public void parseCommand_eventCommandWithoutArguments_exceptionThrown() {
        try {
            Parser.parseCommand("event ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Please provide a proper period for the event task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithoutFrom_exceptionThrown() {
        try {
            Parser.parseCommand("event /to 2023-09-09");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Please provide a proper period for the event task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithoutTo_exceptionThrown() {
        try {
            Parser.parseCommand("event /from 2023-08-08");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Please provide a proper period for the event task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithEmptyTo_exceptionThrown() {
        try {
            Parser.parseCommand("event /from /to ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Please provide a proper period for the event task", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithoutDescription_exceptionThrown() {
        try {
            Parser.parseCommand("event /from 2023-08-08 /to 2023-09-09");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! The description of an event task cannot be empty", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithInvalidDateRange_exceptionThrown() {
        try {
            Parser.parseCommand("event test /from 2023-09-10 /to 2023-09-09");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! End date of an event should not be earlier than the start date."
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithInvalidFromDate_exceptionThrown() {
        try {
            Parser.parseCommand("event test /from 2023/10/10 /to 2023-09-09");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!! the date format of event is incorrect"
                            + ", please use the format yyyy-mm-dd"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithInvalidToDate_exceptionThrown() {
        try {
            Parser.parseCommand("event test /from 2023-10-10 /to 2023/09/09");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!! the date format of event is incorrect"
                            + ", please use the format yyyy-mm-dd"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommand_success() throws DukeException {
        assertEquals(new DeleteCommand(1), Parser.parseCommand("delete 1"));
    }

    @Test
    public void parseCommand_deleteCommandWithNonInteger_exceptionThrown() {
        try {
            Parser.parseCommand("delete sh");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of a delete command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommandWithMultipleArguments_exceptionThrown() {
        try {
            Parser.parseCommand("delete 1 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Invalid argument of a delete command"
                    , e.getMessage());
        }
    }

    @Test
    public void parseCommand_findCommand_success() throws DukeException {
        assertEquals(new FindCommand("test"), Parser.parseCommand("find test"));
    }

    @Test
    public void parseCommand_findCommandWithoutDescription_exceptionThrown() {
        try {
            Parser.parseCommand("find   ");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! Please provide an input to find", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unrecognisedCommand_exceptionThrown() {
        try {
            Parser.parseCommand("blah");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops!!! I'm sorry, but I don't know what that means :-("
                    , e.getMessage());
        }
    }
}

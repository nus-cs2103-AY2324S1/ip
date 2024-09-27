package duke;

import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.command.AddTaskCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void invalidInput_exceptionThrown() {
        try {
            Parser.parse("invalid input");
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_INPUT + Ui.LINE, e.getMessage());
        }
    }

    @Test
    public void parseByeCommand_success() {
        try {
            assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void parseMarkCommand_success() {
        try {
            assertInstanceOf(MarkCommand.class, Parser.parse("mark 2"));
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void parseMarkCommand_noIndexGiven_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            // should not throw
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_MARK + Ui.LINE, e.getMessage());
        }
    }

    @Test
    public void parseDeadlineCommand_success() {
        try {
            assertInstanceOf(AddTaskCommand.class, Parser.parse("deadline assignment /by 2023-09-15 2359"));
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void parseDeadlineCommand_noDeadlineGiven_exceptionThrown() {
        try {
            Parser.parse("deadline assignment");
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_DEADLINE + Ui.LINE, e.getMessage());
        }
    }
}

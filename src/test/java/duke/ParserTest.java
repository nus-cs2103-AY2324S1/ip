package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

public class ParserTest {

    @Test
    public void parse_validListInput_success() {
        try {
            String validInput = "list";
            Command command = Parser.parse(validInput);
            assertEquals(ListCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_validMarkInput_success() {
        try {
            String validInput = "mark 1";
            Command command = Parser.parse(validInput);
            assertEquals(MarkCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidMarkInput_exceptionThrown() {
        try {
            String invalidInput = "mark";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include the index of the task you wish to mark",
                    e.getMessage());
        }
    }

    @Test
    public void parse_validUnmarkInput_success() {
        try {
            String validInput = "unmark 1";
            Command command = Parser.parse(validInput);
            assertEquals(UnmarkCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidUnmarkInput_exceptionThrown() {
        try {
            String invalidInput = "unmark";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include the index of the task you wish to unmark",
                    e.getMessage());
        }
    }

    @Test
    public void parse_validDeleteInput_success() {
        try {
            String validInput = "delete 1";
            Command command = Parser.parse(validInput);
            assertEquals(DeleteCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidDeleteInput_exceptionThrown() {
        try {
            String invalidInput = "delete";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include the index of the task you wish to delete",
                    e.getMessage());
        }
    }

    @Test
    public void parse_validTodoInput_success() {
        try {
            String validInput = "todo read book";
            Command command = Parser.parse(validInput);
            assertEquals(TodoCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidTodoInput_exceptionThrown() {
        try {
            String invalidInput = "todo";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include details of this task", e.getMessage());
        }
    }

    @Test
    public void parse_validDeadlineInput_success() {
        try {
            String validInput = "deadline homework /by 01/01/2024 2359";
            Command command = Parser.parse(validInput);
            assertEquals(DeadlineCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidDeadlineInput_exceptionThrown() {
        try {
            String invalidInput = "deadline";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include details of this task", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineInputMissingDeadline_exceptionThrown() {
        try {
            String invalidInput = "deadline homework";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include the deadline of this task", e.getMessage());
        }
    }

    @Test
    public void parse_validEventInput_success() {
        try {
            String validInput = "event meeting /from 01/01/2024 0800 /to 01/01/2024 1000";
            Command command = Parser.parse(validInput);
            assertEquals(EventCommand.class, command.getClass());
        } catch (DukeException e) {
            System.out.println("Unexpected DukeException was thrown");
            fail();
        }
    }

    @Test
    public void parse_invalidEventInput_exceptionThrown() {
        try {
            String invalidInput = "event";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include details of this task", e.getMessage());
        }
    }

    @Test
    public void parse_invalidEventInputMissingStart_exceptionThrown() {
        try {
            String invalidInput = "event meeting";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include when the event starts", e.getMessage());
        }
    }

    @Test
    public void parse_invalidEventInputMissingEnd_exceptionThrown() {
        try {
            String invalidInput = "event meeting /from 01/01/2024 0800";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command! Please include when the event ends", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            String invalidInput = "blah";
            Parser.parse(invalidInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Sorry! I do not recognise this command", e.getMessage());
        }
    }
}

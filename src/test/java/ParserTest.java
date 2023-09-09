import gbot.Parser;
import gbot.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import exceptions.GBotException;
import exceptions.TodoException;
import exceptions.DeadlineException;
import exceptions.EventException;

public class ParserTest {
    @Test
    public void emptyCommandTest() {
        Assertions.assertEquals("Please enter a command.", Parser.parse("", new TaskList()));
    }
    @Test
    public void parse_invalidCommand_errorMessageShown() {
        try {
            assertEquals("", Parser.parse("invalid ", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Sorry. I don't understand the command", e.getMessage());
        }
    }
    @Test
    public void parseMark_emptyField_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("mark ", new TaskList()));
    }
    @Test
    public void parseMark_invalidTask_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("mark invalid", new TaskList()));
    }
    @Test
    public void parseUnmark_emptyField_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("unmark ", new TaskList()));
    }
    @Test
    public void parseUnmark_invalidTask_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("unmark invalid", new TaskList()));
    }
    @Test
    public void parseTodo_emptyField_todoExceptionThrown() {
        try {
            assertEquals("", Parser.parse("todo ", new TaskList()));
            fail();
        } catch (TodoException e) {
            assertEquals("Invalid format for Todo task. Please adhere to the following:\n" +
                    "todo (task)", e.getMessage());
        }
    }
    @Test
    public void parseDeadline_emptyField_deadlineExceptionThrown() {
        try {
            assertEquals("", Parser.parse("deadline ", new TaskList()));
            fail();
        } catch (DeadlineException e) {
            assertEquals("Invalid format for Deadline task. Please adhere to the following:\n" +
                    "deadline (task) /by (deadline)", e.getMessage());
        }
    }
    @Test
    public void parseEvent_emptyField_eventExceptionThrown() {
        try {
            assertEquals("", Parser.parse("event ", new TaskList()));
            fail();
        } catch (EventException e) {
            assertEquals("Invalid format for Event task. Please adhere to the following:\n" +
                    "event (task) /from (start) /to (end)", e.getMessage());
        }
    }
    @Test
    public void parseDelete_emptyField_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("delete ", new TaskList()));
    }
    @Test
    public void parseDelete_invalidTask_errorMessageShown() {
        assertEquals("Please enter a task number.", Parser.parse("delete invalid", new TaskList()));
    }
}
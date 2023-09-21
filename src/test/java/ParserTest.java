import gbot.Parser;
import gbot.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import exceptions.GBotException;

public class ParserTest {
    @Test
    public void emptyCommandTest() {
        try {
            assertEquals("", Parser.parse("", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Please enter a command.", e.getMessage());
        }
    }
    @Test
    public void parse_invalidCommand_errorMessageShown() {
        try {
            assertEquals("", Parser.parse("invalid ", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Sorry. My command range is simply limited, I don't understand the command.",
                    e.getMessage());
        }
    }
    @Test
    public void parseMark_emptyField_errorMessageShown() {
        assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                "Kindly enter a valid task number.", Parser.parse("mark ", new TaskList()));
    }
    @Test
    public void parseMark_invalidTask_errorMessageShown() {
        try {
            assertEquals("", Parser.parse("mark invalid ", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                    "Kindly enter a valid task number.", e.getMessage());
        }
    }
    @Test
    public void parseUnmark_emptyField_errorMessageShown() {
        assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                "Kindly enter a valid task number.", Parser.parse("unmark ", new TaskList()));
    }
    @Test
    public void parseUnmark_invalidTask_errorMessageShown() {
        try {
            assertEquals("", Parser.parse("unmark invalid ", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                    "Kindly enter a valid task number.", e.getMessage());
        }
    }
    @Test
    public void parseTodo_emptyField_todoExceptionThrown() {
        assertEquals("I apologise. Kindly input a task description.",
                Parser.parse("todo ", new TaskList()));
    }
    @Test
    public void parseDeadline_emptyField_deadlineExceptionThrown() {
        assertEquals("I apologise for correcting you. Kindly follow the following:\n" +
                "deadline (task) /by (deadline in YYYY-MM-DD)\n" +
                "eg. deadline return book /by 2023-09-21",
                Parser.parse("deadline ", new TaskList()));
    }
    @Test
    public void parseEvent_emptyField_eventExceptionThrown() {
        assertEquals("I apologise for correcting you. Kindly follow the following:\n" +
                "event (task) /from (start in YYYY-MM-DD) /to (end in YYYY-MM-DD)\n" +
                "eg. event attend meeting /from 2023-09-21 /to 2023-09-22",
                Parser.parse("event ", new TaskList()));
    }
    @Test
    public void parseDelete_emptyField_errorMessageShown() {
        assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                "Kindly enter a valid task number.", Parser.parse("delete ", new TaskList()));
    }
    @Test
    public void parseDelete_invalidTask_errorMessageShown() {
        try {
            assertEquals("", Parser.parse("delete invalid ", new TaskList()));
            fail();
        } catch (GBotException e) {
            assertEquals("Although you might not be wrong, I simply do not understand...\n" +
                    "Kindly enter a valid task number.", e.getMessage());
        }
    }
}
package duke.parser;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.message.*;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.templates.MessageTemplates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInputParserTest {
    @Test
    public void parse_byeCommand_setsIsActiveToFalse() {
        UserInputParser.isActive = true;
        assertDoesNotThrow(() -> UserInputParser.parse("bye", new TaskList()));
        assertFalse(UserInputParser.isActive);
    }
    @Test
    public void parse_byeMessage_success() throws Exception {
        assertTrue(UserInputParser.parse("bye", new TaskList()) instanceof ByeMessage);
    }
    @Test
    public void parse_listMessage_success() throws Exception {
        assertTrue(UserInputParser.parse("list", new TaskList()) instanceof TaskListMessage);
    }
    @Test
    public void parse_markMessage_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        assertTrue(UserInputParser.parse("mark 1", taskList) instanceof MarkTaskMessage);
    }
    @Test
    public void parse_unmarkMessage_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", true));
        assertTrue(UserInputParser.parse("unmark 1", taskList) instanceof UnmarkTaskMessage);
    }
    @Test
    public void parse_todoMessage_success() throws Exception {
        assertTrue(UserInputParser.parse("todo 1", new TaskList()) instanceof AddTaskMessage);
    }
    @Test
    public void parse_deadlineMessage_success() throws Exception {
        assertTrue(UserInputParser.parse("deadline 1 /by 1000-01-01 0000",
                new TaskList()) instanceof AddTaskMessage);
    }
    @Test
    public void parse_eventMessage_success() throws Exception {
        assertTrue(UserInputParser.parse(
                "event 1 /from 1000-01-01 0000 /to 1000-01-01 0001", new TaskList()) instanceof AddTaskMessage
        );
    }
    @Test
    public void parse_deleteMessage_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        assertTrue(UserInputParser.parse("delete 1", taskList) instanceof DeleteTaskMessage);
    }
    @Test
    public void parse_invalidDateTime_invalidInputExceptionThrown() throws Exception {
        try {
            UserInputParser.parse("deadline 1 /by 1000-99-01 0000", new TaskList());
            fail("Expected InvalidInputException");
        } catch (InvalidInputException e) {
            assertEquals(MessageTemplates.MESSAGE_INVALID_DATETIME, e.getMessage());
        }
    }
    @Test
    public void parse_invalidEventPeriod_invalidInputExceptionThrown() throws Exception {
        try {
            UserInputParser.parse("event 1 /from 1000-01-01 0001 /to 1000-01-01 0000", new TaskList());
            fail("Expected InvalidInputException");
        } catch (InvalidInputException e) {
            assertEquals(MessageTemplates.MESSAGE_INVALID_EVENT_PERIOD, e.getMessage());
        }
    }
    @Test
    public void parse_invalidTodo_invalidInputExceptionThrown() throws Exception {
        try {
            UserInputParser.parse("todo  ", new TaskList());
            fail("Expected InvalidInputException");
        } catch (InvalidInputException e) {
            assertEquals(MessageTemplates.MESSAGE_INVALID_TODO, e.getMessage());
        }
    }
    @Test
    public void parse_invalidCommand_invalidCommandExceptionThrown() throws Exception {
        try {
            UserInputParser.parse("event 1 /from /to ", new TaskList());
            fail("Expected InvalidCommandException");
        } catch (InvalidCommandException ignored) {

        }
    }
    @Test
    public void parse_invalidIndex_invalidIndexExceptionThrown() throws Exception {
        try {
            UserInputParser.parse("mark 1", new TaskList());
            fail("Expected InvalidIndexException");
        } catch (InvalidIndexException ignored) {

        }
    }
}

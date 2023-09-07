package chatterchicken.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatterchicken.data.task.Task;
import chatterchicken.data.task.Deadline;
import chatterchicken.data.task.Event;
import chatterchicken.data.task.ToDo;
import chatterchicken.data.exception.CCException;

public class ParserTest {

    @Test
    public void parseTask_invalidTaskType_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("invalid", "taskDescription");
        } catch (CCException e) {
            return;
        }
        fail("Task was successfully constructed with empty description.");
    }

    @Test
    public void parseTask_emptyToDoDescription_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("todo", "");
        } catch (CCException e) {
            return;
        }
        fail("ToDo was successfully constructed with empty description.");
    }

    @Test
    public void parseTask_validToDo_returnsToDo() {
        Parser parser = new Parser();
        Task task;
        try {
            task = parser.parseTask("todo", "read book");
        } catch (CCException e) {
            fail(e.getMessage());
            return;
        }
        assertTrue(task instanceof ToDo);
        assertEquals("todo read book", task.getInput());
    }

    @Test
    public void parseTask_emptyDeadlineDescription_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("deadline", "");
        } catch (CCException e) {
            return;
        }
        fail("Deadline was successfully constructed with empty description.");
    }

    @Test
    public void parseTask_emptyDeadlineEndDate_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("deadline", "return book /by ");
        } catch (CCException e) {
            return;
        }
        fail("Deadline was successfully constructed with empty end date.");
    }

    @Test
    public void parseTask_validDeadline_returnsDeadline() {
        Parser parser = new Parser();
        Task task;
        try {
            task = parser.parseTask("deadline", "return book /by 2023-01-09");
        } catch (CCException e) {
            fail(e.getMessage());
            return;
        }
        assertTrue(task instanceof Deadline);
        assertEquals("deadline return book /by 2023-01-09", task.getInput());
    }

    @Test
    public void parseTask_emptyEventDescription_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("event", "");
        } catch (CCException e) {
            return;
        }
        fail("Event was successfully constructed with empty description.");
    }

    @Test
    public void parseTask_emptyEventStartDate_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("event", "holiday /from /to 2023-12-25");
        } catch (CCException e) {
            return;
        }
        fail("Event was successfully constructed with empty start date.");
    }

    @Test
    public void parseTask_emptyEventEndDate_throwsCCException() {
        Parser parser = new Parser();
        try {
            parser.parseTask("event", "holiday /from 2023-12-02 /to ");
        } catch (CCException e) {
            return;
        }
        fail("Event was successfully constructed with empty end date.");
    }

    @Test
    public void parseTask_validEvent_returnsEvent() {
        Parser parser = new Parser();
        Task task;
        try {
            task = parser.parseTask("event", "holiday /from 2023-12-02 /to 2023-12-25");
        } catch (CCException e) {
            fail(e.getMessage());
            return;
        }
        assertTrue(task instanceof Event);
        assertEquals("event holiday /from 2023-12-02 /to 2023-12-25", task.getInput());
    }
}
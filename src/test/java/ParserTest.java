package gbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fails;

public class ParserTest {
    @Test
    public void emptyCommandTest() {
        assertEquals("Please enter a command.", Parser.parse("". new TaskList()));
    }
    @Test
    public void invalidCommandTest() {
        try {
            assertEquals("", Parser.parse("invalid", new TaskList()));
            fails();
        } catch (GBotException e) {
            assertEquals("Sorry. I don't understand the command", e.getMessage());
        }
    }
    @Test
    public void emptyMarkTest() {
        assertEquals("Please enter a task number.", Parser.parse("mark ", new TaskList()));
    }
    @Test
    public void invalidMarkTest() {
        assertEquals("Please enter a task number.", Parser.parse("mark invalid", new TaskList()));
    }
    @Test
    public void emptyUnmarkTest() {
        assertEquals("Please enter a task number.", Parser.parse("unmark ", new TaskList()));
    }
    @Test
    public void invalidUnmarkTest() {
        assertEquals("Please enter a task number.", Parser.parse("unmark invalid", new TaskList()));
    }
    @Test
    public void emptyTodoTest() {
        try {
            assertEquals("", Parser.parse("todo ", new TaskList()));
            fails();
        } catch (TodoException e) {
            assertEquals("Invalid format for Todo task. Please adhere to the following:\n" +
                    "todo (task)", e.getMessage());
        }
    }
    @Test
    public void emptyDeadlineTest() {
        try {
            assertEquals("", Parser.parse("deadline ", new TaskList()));
            fails();
        } catch (DeadlineException e) {
            assertEquals("Invalid format for Deadline task. Please adhere to the following:\n" +
                    "deadline (task) /by (deadline)", e.getMessage());
        }
    }
    @Test
    public void emptyEventTest() {
        try {
            assertEquals("", Parser.parse("event ", new TaskList()));
            fails();
        } catch (EventException e) {
            assertEquals("Invalid format for Event task. Please adhere to the following:\n" +
                    "event (task) /from (start) /to (end)", e.getMessage());
        }
    }
    @Test
    public void emptyDeleteTest() {
        assertEquals("Please enter a task number.", Parser.parse("delete ", new TaskList()));
    }
    @Test
    public void invalidDeleteTest() {
        assertEquals("Please enter a task number.", Parser.parse("delete invalid", new TaskList()));
    }
}
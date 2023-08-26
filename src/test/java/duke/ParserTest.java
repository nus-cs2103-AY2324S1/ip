package duke;

import org.junit.jupiter.api.Test;
import task.Todo;
import task.Event;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void createTaskTest_todo_Exception() {
        String input = "todo";
        try {
            Parser.createTask(input);
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTaskTest_todo_valid() {
        String input = "todo read book";
        try {
            assertEquals(new Todo("read book"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTaskTest_deadline_valid() {
        String input = "deadline return book /by Sunday";
        try {
            assertEquals(new Deadline("return book", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    public void createTaskTest_deadline_invalid_exception() {
        String input = "deadline return book Sunday";
        try {
            assertEquals(new Deadline("return book Sunday", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTaskTest_deadline_DateTime() {
        String input = "deadline return book /by 2/12/2019 1800";
        try {
            assertEquals(new Deadline("return book", "2 Dec 2019"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTaskTest_event_valid() {
        String input = "event project meeting /from Mon 2pm /to 4pm";
        try {
            assertEquals(new Event("project meeting", "Mon 2pm", "4pm"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}

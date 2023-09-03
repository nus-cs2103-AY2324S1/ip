package duke;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void createTaskTestTodoException() {
        String input = "todo";
        try {
            Parser.createTask(input);
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTaskTestTodoValid() {
        String input = "todo read book";
        try {
            assertEquals(new Todo("read book"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTaskTestDeadlineValid() {
        String input = "deadline return book /by Sunday";
        try {
            assertEquals(new Deadline("return book", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    public void createTaskTestDeadlineInvalidException() {
        String input = "deadline return book Sunday";
        try {
            assertEquals(new Deadline("return book Sunday", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTaskTestDeadlineDateTime() {
        String input = "deadline return book /by 2/12/2019 1800";
        try {
            assertEquals(new Deadline("return book", "2 Dec 2019"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTaskTestEventValid() {
        String input = "event project meeting /from Mon 2pm /to 4pm";
        try {
            assertEquals(new Event("project meeting", "Mon 2pm", "4pm"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}

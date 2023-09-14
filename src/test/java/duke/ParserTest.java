package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    @Test
    public void createTask_invalidTodoArgument_exceptionThrown() {
        String input = "todo";
        try {
            Parser.createTask(input);
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTask_validTodo_successfulToDoCreation() {
        String input = "todo read book";
        try {
            assertEquals(new Todo("read book"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createTask_validDeadline_successfulDeadlineCreation() {
        String input = "deadline return book /by Sunday";
        try {
            assertEquals(new Deadline("return book", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    public void createTask_missingByClauseDeadline_invalidExceptionThrown() {
        String input = "deadline return book Sunday";
        try {
            assertEquals(new Deadline("return book Sunday", "Sunday"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTask_validDate_successfulDeadlineDatCreation() {
        String input = "deadline return book /by 2/12/2019 1800";
        try {
            assertEquals(new Deadline("return book", "2 Dec 2019"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("Invalid deadline task!", e.getMessage());
        }
    }

    @Test
    public void createTask_validEvent_successfulEventCreation() {
        String input = "event project meeting /from Mon 2pm /to 4pm";
        try {
            assertEquals(new Event("project meeting", "Mon 2pm", "4pm"), Parser.createTask(input));
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}

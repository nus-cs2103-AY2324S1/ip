package duke.data.task.builder;

import duke.data.task.Task;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBuilderTest {
    @Test
    public void buildFromStringTest() throws DukeException {
        TodoBuilder todoBuilder = new TodoBuilder();
        Task t = todoBuilder.buildFromString("todo return book");
        assertEquals("[T][ ] return book.  (tags: todo)", t.toString());
        try {
            todoBuilder.buildFromString("todo ");
        } catch (DukeException e) {
            assertEquals("Invalid Input: Description cannot be empty", e.getMessage());
        }
    }
}

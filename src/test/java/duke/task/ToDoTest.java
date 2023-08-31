package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ToDoTest {
    private ToDo task = new ToDo("task");
    @Test
    public void createToDo_success() {
        assertEquals("[T][ ] task", task.toString());
    }

    @Test
    public void markToDo_success() {
        task.markDone();
        assertEquals("[T][X] task", task.toString());
    }
}

package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListConstructorTest() throws DukeException {
        TaskList test = new TaskList();
        assertEquals(0, test.size(), "Initial size of the task list should be zero");

        ToDo toDo = new ToDo("todo");
        test.add(toDo);
        assertEquals(toDo, test.get(0), "Test insert todo task into list");
        assertEquals(1, test.size(), "Test insert todo task into list");

        test.delete(0);
        assertEquals(0, test.size(), "Test deleting of task from list");
    }
}

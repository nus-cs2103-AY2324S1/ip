package duke.task;

import duke.DukeException;
import duke.utils.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {
    @Test
    public void markTaskDone_success() throws DukeException {
        TaskList task = new TaskList();
        task.addTodoTask("test");
        assertEquals(task.getTasks().get(0).taskStatus(), " ");

        task.markTaskDone(0);
        assertEquals(task.getTasks().get(0).taskStatus(), "X");
    }

    @Test
    public void markNotDone_success() throws DukeException {
        TaskList task = new TaskList();
        task.addTodoTask("test");
        assertEquals(task.getTasks().get(0).taskStatus(), " ");

        task.markTaskDone(0);
        assertEquals(task.getTasks().get(0).taskStatus(), "X");

        task.unmarkTask(0);
        assertEquals(task.getTasks().get(0).taskStatus(), " ");
    }

    @Test
    public void toStringToDo_success() throws DukeException {
        TaskList task = new TaskList();
        task.addTodoTask("test");
        assertEquals(task.getTasks().get(0).toString(), "[T] [ ] test");

        task.markTaskDone(0);
        assertEquals(task.getTasks().get(0).toString(), "[T] [X] test");

        task.unmarkTask(0);
        assertEquals(task.getTasks().get(0).toString(), "[T] [ ] test");
    }
}

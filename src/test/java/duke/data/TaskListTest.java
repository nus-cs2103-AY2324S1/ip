package duke.data;

import duke.data.exception.DukeException;
import duke.data.TaskList;
import duke.data.task.ToDo;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void TaskList_addTask_success() {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTasklist().size());
        tasklist.addTodo("this");
        assertEquals(1, tasklist.getTasklist().size());
    }

    public void TaskList_deleteTask_success() throws DukeException {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTasklist().size());
        tasklist.addTodo("this");
        assertEquals(1, tasklist.getTasklist().size());
        tasklist.deleteTask(1);
        assertEquals(1, tasklist.getTasklist().size());
    }

}

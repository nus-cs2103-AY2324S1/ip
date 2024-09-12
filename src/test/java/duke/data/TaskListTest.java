package duke.data;

import duke.data.exception.DukeException;


import org.junit.jupiter.api.Test;
import duke.data.task.Task;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void TaskList_addTask_success() {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTaskList().size());
        tasklist.addToDo("this");
        assertEquals(1, tasklist.getTaskList().size());
    }

    @Test
    public void TaskList_deleteTask_success() throws DukeException {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTaskList().size());
        tasklist.addToDo("this");
        assertEquals(1, tasklist.getTaskList().size());
        tasklist.deleteTask(1);
        assertEquals(0, tasklist.getTaskList().size());
    }

    @Test
    public void TaskList_markTask_success() throws DukeException {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTaskList().size());
        tasklist.addToDo("this");
        assertEquals(1, tasklist.getTaskList().size());
        tasklist.markTask(1);
        assertEquals("X", tasklist.getTaskList().get(0).getStatusIcon());
    }

    @Test
    public void TaskList_unmarkTask_success() throws DukeException {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getTaskList().size());
        tasklist.addToDo("this");
        assertEquals(1, tasklist.getTaskList().size());
        tasklist.markTask(1);
        assertEquals("X", tasklist.getTaskList().get(0).getStatusIcon());
        tasklist.unmarkTask(1);
        assertEquals(" ", tasklist.getTaskList().get(0).getStatusIcon());
    }

    @Test
    public void TaskList_viewSchedule_success() throws DukeException {
        TaskList tasklist = new TaskList();
        tasklist.addDeadline("this", "19-July");
        ArrayList<Task> res = tasklist.viewSchedule("19-July");
        assertEquals("this", res.get(0).getDescription());
    }

    @Test
    public void TaskList_searchTasks_success() throws DukeException {
        TaskList tasklist = new TaskList();
        tasklist.addDeadline("this", "19-July");
        ArrayList<Task> res = tasklist.searchTasks("this");
        assertEquals("this", res.get(0).getDescription());
    }

}

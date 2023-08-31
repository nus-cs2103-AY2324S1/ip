package duke;

import duke.exceptions.CommandDetailException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void testAddTask() throws CommandDetailException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        taskList.deleteTask(0);
        assertEquals(taskList.size(), 0);
    }

    @Test
    void testMarkTask() throws CommandDetailException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    void testUnmarkTask() throws CommandDetailException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    void testGetTask() throws CommandDetailException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    void testGetTaskException() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        assertThrows(CommandDetailException.class, () -> {
            taskList.getTask(3);
        });
    }
}

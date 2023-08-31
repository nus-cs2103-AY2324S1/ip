package duke;

import duke.exceptions.CommandDetailException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    void testAddTask() throws CommandDetailException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        taskList.add(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    void testDeleteTask() throws CommandDetailException {
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

    @Test
    void testDeleteTaskException() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        assertThrows(CommandDetailException.class, () -> {
            taskList.deleteTask(3);
        });
    }

    @Test
    void testMarkTaskException() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        assertThrows(CommandDetailException.class, () -> {
            taskList.markTask(3);
        });
    }

    @Test
    void testUnmarkTaskException() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("test");
        assertThrows(CommandDetailException.class, () -> {
            taskList.unmarkTask(3);
        });
    }
}

package duke;

import duke.task.Task;
import duke.task.TaskTest;

import java.util.ArrayList;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void testSizeEmptyList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void testSizeNonEmptyList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.size());
    }

    @Test
    public void testGetTaskValidIndex() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        tasks.add(task1);
        tasks.add(task2);

        TaskList taskList = new TaskList(tasks);

        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    public void testGetTaskInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> taskList.getTask(-1));
    }

    @Test
    public void testAddTask() throws DukeException{
        TaskList taskList = new TaskList();
        Task task = new Todo("Task");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testMarkTaskValidIndex() throws DukeException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Task");
        taskList.addTask(task);

        taskList.markTask(0);
        assertEquals(task.getStatusIcon(), "X");
    }

    @Test
    public void testMarkTaskInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> taskList.markTask(0));
    }

    @Test
    public void testUnmarkTaskValidIndex() throws DukeException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Task");
        taskList.addTask(task);

        taskList.unmarkTask(0);
        assertEquals(task.getStatusIcon(), " ");
    }

    @Test
    public void testUnmarkTaskInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> taskList.unmarkTask(0));
    }

    @Test
    public void testDeleteTaskValidIndex() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        tasks.add(task1);
        tasks.add(task2);

        TaskList taskList = new TaskList(tasks);

        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> taskList.deleteTask(0));
    }
}

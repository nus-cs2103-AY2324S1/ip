package duke.utils;

import duke.DukeException;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
    }

    @Test
    public void deleteTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        taskList.deleteTask(1);
        assertEquals(taskList.getSize(), 0);
    }

    @Test
    public void markTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        taskList.markTask(1);
        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTasks().get(0).getStatusIcon(), "X");
    }

    @Test
    public void unmarkTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        taskList.markTask(1);
        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTasks().get(0).getStatusIcon(), "X");
        taskList.unmarkTask(1);
        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTasks().get(0).getStatusIcon(), " ");
    }
}

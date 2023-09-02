package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void addTask_success() {
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
    public void deleteTask_invalidTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        try {
            taskList.deleteTask(2);
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! No such task exists.");
        }
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
    public void markTask_invalidTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        try {
            taskList.markTask(2);
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! No such task exists.");
        }
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

    @Test
    public void unmarkTask_invalidTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
        taskList.addTodo("test");
        assertEquals(taskList.getSize(), 1);
        try {
            taskList.unmarkTask(2);
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! No such task exists.");
        }
    }
}

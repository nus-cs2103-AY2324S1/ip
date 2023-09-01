package duke.task;

import duke.core.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void getTasks_emptyList_success() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.size(), 0);
        assertEquals(tasks.getTasks().count(), 0);
    }

    @Test
    public void markAsDone_negativeIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.markAsDone(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }

    @Test
    public void markAsDone_validIndex_success() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            assertEquals(" ", task.getStatusIcon());
            tasks.markAsDone(0);
            assertEquals("X", task.getStatusIcon());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsDone_indexGreaterThanSize_exceptionThrown() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            assertEquals(" ", task.getStatusIcon());
            tasks.markAsDone(1);
            assertEquals("X", task.getStatusIcon());
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }

    @Test
    public void markAsUndone_negativeIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.markAsUndone(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }

    @Test
    public void markAsUndone_validIndex_success() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            tasks.markAsDone(0);
            assertEquals("X", task.getStatusIcon());
            tasks.markAsUndone(0);
            assertEquals(" ", task.getStatusIcon());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markAsUndone_indexGreaterThanSize_exceptionThrown() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            tasks.markAsDone(1);
            assertEquals("X", task.getStatusIcon());
            tasks.markAsUndone(1);
            assertEquals(" ", task.getStatusIcon());
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_negativeIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            tasks.deleteTask(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validIndex_success() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            tasks.deleteTask(0);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(0, tasks.size());
    }

    @Test
    public void deleteTask_indexGreaterThanSize_exceptionThrown() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Task");
        tasks.addTask(task);
        try {
            tasks.deleteTask(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task number provided does not exist.", e.getMessage());
        }
    }
}

package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Test class for the TaskList class, which manages a list of tasks in Duke.
 */
public class TaskListTest {

    @Test
    public void testListSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.listSize());

        taskList.addTask(new Todo("Buy groceries", false));
        assertEquals(1, taskList.listSize());

        taskList.addTask(new Todo("Do homework", false));
        assertEquals(2, taskList.listSize());
    }

    @Test
    public void testAddTask() throws DukeException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Buy groceries", false);
        taskList.addTask(todo);

        assertEquals(todo, taskList.getTask(0));
        assertEquals(1, taskList.listSize());
    }

    @Test
    public void testGetTask() throws DukeException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Buy groceries", false);
        taskList.addTask(todo);

        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() throws DukeException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Buy groceries", false);
        taskList.addTask(todo);

        assertEquals(1, taskList.listSize());
        taskList.deleteTask(0);
        assertEquals(0, taskList.listSize());
    }

    @Test
    public void testMarkTaskAsDone() throws DukeException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Buy groceries", false);
        taskList.addTask(todo);

        assertFalse(todo.getDone());
        taskList.markTaskAsDone(0);
        assertTrue(todo.getDone());
    }

    @Test
    public void testMarkTaskAsUndone() throws DukeException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Buy groceries", false);
        taskList.addTask(todo);

        assertFalse(todo.getDone());
        taskList.markTaskAsDone(0);
        assertTrue(todo.getDone());

        taskList.markTaskAsUndone(0);
        assertFalse(todo.getDone());
    }

    @Test
    public void testContainsDuplicate() {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("Buy groceries", false);
        Todo todo2 = new Todo("Buy groceries", false);
        taskList.addTask(todo1);

        assertTrue(taskList.containsDuplicate(todo2));
    }
}

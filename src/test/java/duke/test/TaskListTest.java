package duke.test;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the TaskList class, which manages a list of tasks in Duke.
 */
public class TaskListTest {

    /**
     * Test case for adding a task to the TaskList and checking the list size.
     */
    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("a", true));
        assertEquals(1, tasks.listSize());
    }

    /**
     * Test case for deleting a task from the TaskList and checking the list size.
     *
     * @throws DukeException If there is an issue with deleting the task.
     */
    @Test
    public void testDelete() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("a", true));
        tasks.addTask(new Todo("b", true));
        tasks.addTask(new Todo("c", true));
        tasks.deleteTask(1);
        assertEquals(2, tasks.listSize());
    }
}

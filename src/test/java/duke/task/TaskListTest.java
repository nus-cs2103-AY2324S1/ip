package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

/**
 * A JUnit test class for the TaskList class.
 */
public class TaskListTest {
    private TaskList taskList;

    /**
     * Sets up the test fixture.
     * Creates a new TaskList object with an empty ArrayList.
     */
    @BeforeEach
    public void setup() {
        taskList = new TaskList(new ArrayList<>());
    }

    /**
     * Tests the addTask method of the TaskList class.
     * Adds a new task to the task list and checks if the size of the task list is 1.
     */
    @Test
    public void testAddTask() {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests the deleteTask method of the TaskList class.
     * Adds a new task to the task list, deletes it, and checks if the size of the task list is 0.
     * @throws DukeException If an error occurs while deleting the task.
     */
    @Test
    public void testDeleteTask() throws DukeException {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);

        taskList.deleteTask(0);
        assertEquals(0, taskList.getTasks().size());
    }

    /**
     * Tests the deleteTask method of the TaskList class with an invalid task index.
     * Expects a DukeException to be thrown.
     */
    @Test
    public void testDeleteInvalidTask() {
        assertThrows(DukeException.class, () -> taskList.deleteTask(0));
    }

    /**
     * Tests the get method of the TaskList class.
     * Adds a new task to the task list, retrieves it, and checks if its string representation is correct.
     * @throws DukeException If an error occurs while retrieving the task.
     */
    @Test
    public void testGetTask() throws DukeException {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);

        Task retrievedTask = taskList.get(0);
        assertEquals("[T][ ] Test Task", retrievedTask.toString());
    }

    /**
     * Tests the get method of the TaskList class with an invalid task index.
     * Expects a DukeException to be thrown.
     */
    @Test
    public void testGetInvalidTask() {
        assertThrows(DukeException.class, () -> taskList.get(0));
    }
}

package duke;

import duke.task.Task;
import duke.task.TaskType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the unit tests for the TaskList class.
 */
public class TaskListTest {

    /** TaskList object to be used for testing purposes. */
    private TaskList taskList;

    /**
     * Initializes a new TaskList object for each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests the getSize method when the TaskList is empty.
     */
    @Test
    public void getSize_emptyList() {
        assertEquals(0, taskList.getSize());
    }

    /**
     * Tests the addition of a single task to the TaskList.
     */
    @Test
    public void addTask_addOneTask() {
        taskList.addTask(new Task("Test Task", TaskType.TODO));
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests the deleteTask method by adding a task and then deleting it.
     * Ensures that the deleted task is returned and the size of the list is updated.
     */
    @Test
    public void deleteTask_taskAdded_() {
        Task task = new Task("Test Task", TaskType.TODO);
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());

        Task removedTask = taskList.deleteTask(0);
        assertEquals(task, removedTask);
        assertEquals(0, taskList.getSize());
    }
}

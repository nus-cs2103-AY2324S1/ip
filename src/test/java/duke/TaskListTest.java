package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TaskListTest class contains unit tests for the TaskList class.
 */
public class TaskListTest {
    private TaskList tasks = new TaskList();

    /**
     * Test the addTask method of the TaskList class.
     */
    @Test
    public void testAddTask() {
        int orgCount = tasks.getAll().size();
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        assertEquals(orgCount + 1, tasks.getAll().size());
    }

    /**
     * Test the removeTask method of the TaskList class.
     */
    @Test
    public void testRemoveTask() {
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        int orgCount = tasks.getAll().size();
        tasks.removeTask(0);
        assertEquals(orgCount - 1, tasks.getAll().size());
    }
}
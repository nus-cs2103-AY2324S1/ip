package duke;

import static duke.Task.Type.TODO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests adding a task to the TaskList.
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test Task", TODO);
        taskList.addTask(task);
        assertEquals(1, taskList.numTasks());
        assertEquals(task, taskList.getTask(0));
    }

    /**
     * Tests marking a task as done.
     */
    @Test
    public void testMarkAsDone() {
        Task task = new Task("Sample Task", TODO);
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }
}

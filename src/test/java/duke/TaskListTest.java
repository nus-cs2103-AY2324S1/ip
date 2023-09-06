package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;


/**
 * Test class for the TaskList class.
 */
public class TaskListTest {

    private TaskList taskList = new TaskList();

    /**
     * Test adding a task to the task list.
     */
    @Test
    public void testAddTask() {
        int initialTaskCount = taskList.taskCount();
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        assertEquals(initialTaskCount + 1, taskList.taskCount());
    }

    /**
     * Test marking a task as done in the task list.
     */
    @Test
    public void testMarkTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    /**
     * Test marking a task as not done in the task list.
     */
    @Test
    public void testUnMarkTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
        taskList.unMarkTask(0);
        assertFalse(taskList.getTask(0).isDone());
    }

    /**
     * Test deleting a task from the task list.
     */
    @Test
    public void testDeleteTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        int initialTaskCount = taskList.taskCount();
        taskList.deleteTask(0);
        assertEquals(initialTaskCount - 1, taskList.taskCount());
    }

    /**
     * Test getting the count of tasks in the task list.
     */
    @Test
    public void testTaskCount() {
        int initialTaskCount = taskList.taskCount();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(initialTaskCount + 2, taskList.taskCount());
    }

    /**
     * Test getting a specific task from the task list.
     */
    @Test
    public void testGetTask() {
        int initialTaskCount = taskList.taskCount();
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(initialTaskCount));
    }
}

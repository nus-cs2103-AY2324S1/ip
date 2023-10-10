package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        // Create a TaskList
        TaskList taskList = new TaskList();

        // Create a task
        Todo task = new Todo("Test Task");

        // Add the task to the TaskList
        taskList.add(task);

        // Check if the task was added successfully
        assertEquals(1, taskList.size());
    }

    @Test
    public void testRemoveTask() {
        // Create a TaskList
        TaskList taskList = new TaskList();

        // Create a task
        Todo task = new Todo("Test Task");

        // Add the task to the TaskList
        taskList.add(task);

        // Remove the task from the TaskList
        Task removedTask = taskList.remove(0);

        // Check if the task was removed successfully
        assertEquals(0, taskList.size());
        assertEquals("Test Task", removedTask.getDescription());
    }
}

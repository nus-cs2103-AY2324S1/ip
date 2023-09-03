package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());

        try {
            taskList.addTask(new ToDo("Test Task"));
            assertEquals(1, taskList.getSize());
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveTask() {
        // Create a TaskList with some tasks
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("Task 1"));
            tasks.add(new Deadline("Task 2", "2023-08-23 1800"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.getSize());

        // Remove a task
        taskList.removeTask(0);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testMarkTask() {
        // Create a TaskList with a task
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("Task 1"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        TaskList taskList = new TaskList(tasks);

        // Mark the task as done
        taskList.markTask(0);

        // Check if the task is marked as done
        assertTrue(tasks.get(0).isDone());
    }

    @Test
    public void testUnmarkTask() {
        // Create a TaskList with a done task
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("Task 1"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
        tasks.get(0).markAsDone();

        TaskList taskList = new TaskList(tasks);

        // Unmark the task as done
        taskList.unmarkTask(0);

        // Check if the task is marked as not done
        assertFalse(tasks.get(0).isDone());
    }
}
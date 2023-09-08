package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskListTest class contains unit tests for the TaskList class.
 */
public class TaskListTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    /**
     * Test the addTask method of the TaskList class.
     */
    @Test
    public void testAddTask() {
        int orgCount = tasks.getTasks().size();
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        assertEquals(orgCount + 1, tasks.getTasks().size());
    }

    /**
     * Test the removeTask method of the TaskList class.
     */
    @Test
    public void testRemoveTask() {
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        int orgCount = tasks.getTasks().size();
        tasks.deleteTask(0);
        assertEquals(orgCount - 1, tasks.getTasks().size());
    }
}

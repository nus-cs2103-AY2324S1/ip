package duke;

import duke.exception.TaskNotFoundException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private final TaskList tasks = new TaskList();

    @Test
    public void testAddTask() {
        assertEquals(0, tasks.numTasks());

        tasks.addTask(new Todo("new task"));
        assertEquals(1, tasks.numTasks());
    }

    @Test
    public void testGetTask() {
        try {
            tasks.getTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("Task not found!", e.getMessage());
        }

        tasks.addTask(new Todo("new task"));
        try {
            assertEquals("new task", tasks.getTask(0).getDescription());
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            tasks.deleteTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("Task not found!", e.getMessage());
        }

        tasks.addTask(new Todo("new task"));
        try {
            tasks.deleteTask(0);
            assertEquals(0, tasks.numTasks());
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMarkTask() {
        tasks.addTask(new Todo("new task"));
        try {
            assertFalse(tasks.getTask(0).getIsDone());
            tasks.markTask(0);
            assertTrue(tasks.getTask(0).getIsDone());
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

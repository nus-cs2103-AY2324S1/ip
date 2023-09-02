package duke;

import org.junit.jupiter.api.Test;
import static duke.Task.Type.TODO;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test Task", TODO);
        taskList.addTask(task);
        assertEquals(1, taskList.numTasks());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Sample Task", Task.Type.TODO);
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }
}
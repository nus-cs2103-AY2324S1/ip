package duke;

import duke.task.Task;
import duke.task.TaskList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        int initialTaskCount = taskList.taskCount();
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        assertEquals(initialTaskCount + 1, taskList.taskCount());
    }

    @Test
    public void testMarkTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void testUnMarkTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
        taskList.unMarkTask(0);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        int initialTaskCount = taskList.taskCount();
        taskList.deleteTask(0);
        assertEquals(initialTaskCount - 1, taskList.taskCount());
    }

    // You can add more tests as needed
    @Test
    public void testTaskCount() {
        int initialTaskCount = taskList.taskCount();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(initialTaskCount + 2, taskList.taskCount());
    }

    @Test
    public void testGetTask() {
        int initialTaskCount = taskList.taskCount();
        Task task = new Task("Sample Task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(initialTaskCount));
    }
}

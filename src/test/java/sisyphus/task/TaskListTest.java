package sisyphus.task;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.getSize());

        // Add some tasks and check the size
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void testAddTaskAndGetTask() {
        ToDo task = new ToDo("Sample Task");
        taskList.addTask(task);

        assertEquals(1, taskList.getSize());

        // Retrieve the task and check if it's the same object
        Task retrievedTask = taskList.getTask(0);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void testMarkTask() {
        ToDo task = new ToDo("Task to be marked");
        taskList.addTask(task);

        assertEquals(false, task.isDone);

        taskList.markTask(0);

        assertEquals(true, task.isDone);
    }

    @Test
    public void testUnmarkTask() {
        ToDo task = new ToDo("Task to be unmarked", true);
        taskList.addTask(task);

        assertEquals(true, task.isDone);

        taskList.unmarkTask(0);

        assertEquals(false, task.isDone);
    }

    @Test
    public void testDeleteTask() {
        ToDo task = new ToDo("Task to be deleted");
        taskList.addTask(task);

        assertEquals(1, taskList.getSize());

        taskList.deleteTask(0);

        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testGetLastTask() {
        ToDo task1 = new ToDo("Task 1");
        ToDo task2 = new ToDo("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        Task lastTask = taskList.getLastTask();

        assertEquals(task2, lastTask);
    }
}

package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    void getTaskDetails() {
        Task task1 = new Task("Sample Task 1");
        Task task2 = new Task("Sample Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals("[ ] Sample Task 1", taskList.getTaskDetails(0));
        assertEquals("[ ] Sample Task 2", taskList.getTaskDetails(1));
    }

    @Test
    void addTaskTest() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        assertEquals(1, taskList.getTaskCount());
        assertSame(taskList.getTask(0), task);
    }

    @Test
    void deleteTaskTest() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        assertEquals(1, taskList.getTaskCount());

        taskList.deleteTask(0);

        assertEquals(0, taskList.getTaskCount());
        assertNull(taskList.getTask(0));
    }

    @Test
    void markTaskAsDoneTest() {
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        assertFalse(task.isDone());
        taskList.markTaskAsDone(0);
        assertTrue(task.isDone());
    }

    @Test
    void markTaskAsNotDoneTest() {
        Task task = new Task("Sample Task", true);
        taskList.addTask(task);

        assertTrue(task.isDone());
        taskList.markTaskAsNotDone(0);
        assertFalse(task.isDone());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(taskList.isEmpty());

        Task task = new Task("Sample Task");
        taskList.addTask(task);
        assertFalse(taskList.isEmpty());
    }

    @Test
    void toStringTest() {
        assertEquals("Horray!! No tasks in the task list!", taskList.toString());

        Task task1 = new Task("Sample Task 1");
        Task task2 = new Task("Sample Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        String expected = "Here are the tasks in your list:\n1. [ ] Sample Task 1\n2. [ ] Sample Task 2\n";
        assertEquals(expected, taskList.toString());
    }
}
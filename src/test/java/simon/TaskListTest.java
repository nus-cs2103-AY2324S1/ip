package simon;

import org.junit.jupiter.api.Test;
import simon.task.Task;
import simon.task.ToDo;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void markTask_validIndex_taskMarkedAsDone() throws SimonException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Sample Task"));
        Task markedTask = tasks.markTask("mark 1", true);
        assertTrue(markedTask.isDone);
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        assertThrows(SimonException.class, () -> tasks.markTask("100", true));
    }

    @Test
    public void deleteTask_validIndex_taskDeleted() throws SimonException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Sample Task"));
        Task deletedTask = tasks.deleteTask("delete 1");
        assertEquals("Sample Task", deletedTask.taskName);
        assertEquals(0, tasks.getTaskCount());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        assertThrows(SimonException.class, () -> tasks.deleteTask("delete 100"));
    }

    @Test
    public void addTask_multipleTasks_addedSuccessfully() throws SimonException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));
        tasks.addTask(new ToDo("Task 3"));
        assertEquals(3, tasks.getTaskCount());
    }

    @Test
    public void deleteTask_multipleTasks_deletedSuccessfully() throws SimonException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));
        tasks.addTask(new ToDo("Task 3"));

        Task deletedTask1 = tasks.deleteTask("delete 1");
        assertEquals("Task 1", deletedTask1.taskName);
        assertEquals(2, tasks.getTaskCount());

        Task deletedTask2 = tasks.deleteTask("delete 2");
        assertEquals("Task 3", deletedTask2.taskName);
        assertEquals(1, tasks.getTaskCount());
    }

    @Test
    public void markTask_emptyTaskList_exceptionThrown() {
        TaskList tasks = new TaskList();
        assertThrows(SimonException.class, () -> tasks.markTask("mark 1", true));
    }
}
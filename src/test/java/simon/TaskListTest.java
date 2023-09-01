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
}
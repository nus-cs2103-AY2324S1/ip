package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    // We will not modify the Task class as it is simple and does not have any side effects
    @Test
    public void addTask_taskAddedToList() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void removeTask_taskRemovedFromList() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void markDone_taskMarkedAsDone() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        taskList.markDone(0);
        assertTrue(taskList.getTask(0).getIsDone());
    }

    @Test
    public void unmarkDone_taskUnmarked() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        taskList.markDone(0);
        taskList.unmarkDone(0);
        assertFalse(taskList.getTask(0).getIsDone());
    }

    @Test
    public void getTask_retrievesCorrectTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void removeTask_emptyList_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(0));
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sleep");
        taskList.addTask(task);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(1));
    }
}

package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void addTask_numTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test"));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addCompletedTask_numTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test", true));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addCompletedTask_numCompletedTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test", true));
        assertEquals(1, tasks.getNumOfCompletedTasks());
    }

    @Test
    public void addTask_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test"));
        assertEquals(TaskList.TaskType.TASK, tasks.getTaskType(0));
    }

    @Test
    public void addToDo_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals(TaskList.TaskType.TODO, tasks.getTaskType(0));
    }

    @Test
    public void addDeadline_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("test", LocalDateTime.MIN));
        assertEquals(TaskList.TaskType.DEADLINE, tasks.getTaskType(0));
    }

    @Test
    public void addEvent_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Event("test", LocalDateTime.MIN, LocalDateTime.MAX));
        assertEquals(TaskList.TaskType.EVENT, tasks.getTaskType(0));
    }

    @Test
    public void removeTask_numTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test");
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfTasks());
    }

    @Test
    public void removeCompletedTask_numTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test", true);
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfTasks());
    }

    @Test
    public void removeCompletedTask_numCompletedTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test", true);
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfCompletedTasks());
    }

    @Test
    public void checkDuplicates_success() {
        TaskList tasks = new TaskList();
        Task t = new Task("test");
        tasks.add(t);
        assertTrue(tasks.checkDuplicates("test"));
    }

}

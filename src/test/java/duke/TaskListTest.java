package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList tasks = new TaskList();

    @Test
    public void testAddTask() {
        int orgCount = tasks.getAll().size();
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        assertEquals(orgCount + 1, tasks.getAll().size());
    }

    @Test
    public void testRemoveTask() {
        Task newTask = new Task("Test task");
        tasks.addTask(newTask);
        int orgCount = tasks.getAll().size();
        tasks.removeTask(0);
        assertEquals(orgCount - 1, tasks.getAll().size());
    }

}

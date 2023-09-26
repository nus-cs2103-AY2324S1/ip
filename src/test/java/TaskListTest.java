import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(0);
        assertEquals(1, taskList.getCount());
        assertEquals(task2, taskList.getTask(0));
    }
}
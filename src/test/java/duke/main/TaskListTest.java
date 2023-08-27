package duke.main;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_newTask_taskListExpands(){
        TaskList taskList = new TaskList();
        taskList.add(new Task("a"));
        taskList.add(new Task("b"));
        assertEquals(taskList.size(), 2);
    }

    @Test
    public void remove_existingTask_taskListShrinks(){
        TaskList taskList = new TaskList();
        Task a = new Task("a");
        Task b = new Task("b");
        taskList.add(a);
        taskList.add(b);
        Task taskRemoved = taskList.remove(1);
        assertEquals(taskList.size(), 1);
        assertEquals(taskRemoved, b);
    }
}

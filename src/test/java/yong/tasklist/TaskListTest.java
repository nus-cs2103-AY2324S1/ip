package yong.tasklist;
import yong.tasks.Task;
import yong.tasks.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void init() {
        taskList = new TaskList();
    }

    @Test
    public void addTest() {
        Task task = new ToDo("Read book");
        taskList.add(task);

        ArrayList<Task> tasks = taskList.get();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void markAsDoneTest() {
        Task task = new ToDo("Read book");
        taskList.add(task);

        taskList.mark(1);

        assertTrue(task.isDone());
    }

    @Test
    public void unmarkTest() {
        Task task = new ToDo("Read book");
        task.markAsDone();
        taskList.add(task);

        taskList.unmark(1);

        assertTrue(!task.isDone());
    }

    @Test
    public void deleteTest() {
        Task task = new ToDo("Read book");
        taskList.add(task);

        Task deletedTask = taskList.delete(1);

        assertEquals(task, deletedTask);
        assertTrue(taskList.get().isEmpty());
    }

}

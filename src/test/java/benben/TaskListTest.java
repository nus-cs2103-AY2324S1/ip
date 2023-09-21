package benben;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void initialLength_isZero() {
        TaskList tasks = new TaskList();
        Task newTask = new Todo("test todo task");
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void addTask_lengthIncrease() {
        TaskList tasks = new TaskList();
        Task newTask = new Todo("test todo task");
        tasks.add(newTask);
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void removeTask_taskList_lengthDecrease() {
        TaskList tasks = new TaskList();
        Task newTask = new Todo("test todo task two");
        tasks.add(newTask);
        tasks.remove(0);
        assertEquals(tasks.size(), 0);
    }
}

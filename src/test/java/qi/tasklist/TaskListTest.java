package qi.tasklist;

import org.junit.jupiter.api.Test;
import qi.task.Task;
import qi.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testShowTask(){
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        assertEquals("[T][ ] eat", list.showTask(1));
    }

    @Test
    public void testDeleteTask(){
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        Task code = new Todo("code");
        list.addTask(code);
        assertEquals(code, list.deleteTask(2));
    }
}
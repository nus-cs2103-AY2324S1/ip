package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void getTask_containsTask_success() {
        TaskList list = new TaskList();
        Task t1 = new Todo("test1");
        Task t2 = new Todo("test2");
        list.addTask(t1);
        list.addTask(t2);
        assertEquals(t1, list.getTask(0));
        assertEquals(t2, list.getTask(1));
        assertNotEquals(t2, list.getTask(0));
    }

    @Test
    public void getTask_returnSameObject_success() {
        TaskList list = new TaskList();
        Task t1 = new Todo("test1");
        Task t2 = new Todo("test2");
        list.addTask(t1);
        assertEquals(list.getTask(0), t1);
        assertNotEquals(list.getTask(0), t2);
    }

    @Test
    public void getTask_indexOob_exception() {
        TaskList list = new TaskList();
        Task t1 = new Todo("test1");
        list.addTask(t1);
        try {
            list.getTask(123);
            fail();
        } catch (Exception e) {
            assertEquals("Index 123 out of bounds for length 1", e.getMessage());
        }
    }
}

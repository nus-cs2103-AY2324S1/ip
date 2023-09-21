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

    @Test
    public void deleteTask_indexOob_exception() {
        TaskList list = new TaskList();
        try {
            list.getTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }

    }

    @Test
    public void deleteTask_returnSameObject_success() {
        TaskList list = new TaskList();
        Task t1 = new Todo("test1");
        Task t2 = new Todo("test2");
        list.addTask(t1);
        Task t3 = list.deleteTask(0);
        assertEquals(t1, t3);
        assertNotEquals(t2, t3);

    }

    @Test
    public void deleteTask_removesTask_success() {
        TaskList list = new TaskList();
        Task t1 = new Todo("test1");
        list.addTask(t1);
        list.deleteTask(0);
        assertEquals(0, list.size());
    }
}

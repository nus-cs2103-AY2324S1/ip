package bob.task;

import bob.exception.BobException;
import bob.exception.BobInvalidTaskNumberException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void getTask_validIndex_success() {

        try {
            TaskList taskList = new TaskList();
            Task t1 = new Todo("todo task 1");
            Task t2 = new Todo("todo task 2");
            Task t3 = new Todo("todo task 3");
            taskList.addTask(t1);
            taskList.addTask(t2);
            taskList.addTask(t3);
            assertEquals(t1, taskList.getTask(0));
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {

        try {
            TaskList taskList = new TaskList();
            Task t1 = new Todo("todo task 1");
            Task t2 = new Todo("todo task 2");
            Task t3 = new Todo("todo task 3");
            taskList.addTask(t1);
            taskList.addTask(t2);
            taskList.addTask(t3);
            assertEquals(t1, taskList.getTask(100));
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
        }
    }
}

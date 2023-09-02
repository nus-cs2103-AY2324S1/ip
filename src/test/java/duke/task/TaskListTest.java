package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class TaskListTest {
    @Test
    public void add_newTask_success() {
        TaskList taskList = new TaskList();
        assertNotNull(taskList.add(new TodoTask("1", false)));
    }

    @Test
    public void delete_task_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        assertNotNull(taskList.delete(1));
    }

    @Test
    public void delete_task_invalidIndexExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        try {
            taskList.delete(10);
            fail("Expected InvalidIndexException");
        } catch (InvalidIndexException ignored) {
            return;
        }
    }

    @Test
    public void printList_success() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        assertNotNull(taskList.printList());
    }

    @Test
    public void markTask_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        assertNotNull(taskList.markTask(1));
    }

    @Test
    public void markTask_invalidIndexExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        try {
            taskList.markTask(10);
            fail("Expected InvalidIndexException");
        } catch (InvalidIndexException ignored) {
            return;
        }
    }

    @Test
    public void unmarkTask_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", true));
        assertNotNull(taskList.unmarkTask(1));
    }

    @Test
    public void unmarkTask_invalidIndexExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", true));
        try {
            taskList.unmarkTask(10);
            fail("Expected InvalidIndexException");
        } catch (InvalidIndexException ignored) {
            return;
        }
    }

    @Test
    public void toStringStore_success() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("1", false));
        taskList.add(new DeadlinesTask("2", false, "3"));
        taskList.add(new EventsTask("4", false, "5", "6"));
        assertEquals("T,0,1\nD,0,2,3\nE,0,4,5,6\n", taskList.toStringStore());
    }
}

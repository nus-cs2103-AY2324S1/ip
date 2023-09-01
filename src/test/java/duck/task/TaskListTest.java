package duck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    
    @Test
    public void add_validTask_success() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTaskCount());

        taskList.add(new TodoTask("test", false));
        assertEquals(1, taskList.getTaskCount());

        taskList.add(new TodoTask("test", false));
        assertEquals(2, taskList.getTaskCount());
    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.getTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("Error - invalid task number.", e.getMessage());
        }
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.mark(1);
            fail();
        } catch (Exception e) {
            assertEquals("Error - invalid task number.", e.getMessage());
        }
    }

    @Test
    public void unmark_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.mark(1);
            fail();
        } catch (Exception e) {
            assertEquals("Error - invalid task number.", e.getMessage());
        }
    }

    @Test
    public void delete_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.delete(1);
            fail();
        } catch (Exception e) {
            assertEquals("Error - invalid task number.", e.getMessage());
        }
    }
}

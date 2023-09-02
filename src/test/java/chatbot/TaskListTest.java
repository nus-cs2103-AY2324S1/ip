package chatbot;

import chatbot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void taskRep_emptyTaskList_exceptionThrown() {
        try {
            assertEquals("", new TaskList().taskRep(2));
            fail();
        } catch (Exception e) {
            assertEquals("Index 2 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void taskRep_nonEmptyTaskListValidIndex_success() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read"));
        assertEquals("[T][ ] read", taskList.taskRep(0));
    }

    @Test
    public void taskRep_nonEmptyTaskListInvalidIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.add(new Todo("read"));
            assertEquals("[T][ ] read", taskList.taskRep(2));
            fail();
        } catch (Exception e) {
            assertEquals("Index 2 out of bounds for length 1", e.getMessage());
        }
    }

}

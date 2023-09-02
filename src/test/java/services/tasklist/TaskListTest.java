package services.tasklist;

import command.CommandType;
import org.junit.jupiter.api.Test;
import services.TextUi;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    // the following three test cases are testing the add method in TaskList.
    @Test
    public void add_todoTask_success() {
        try {
            TaskList taskList = new TaskList(new StorageDummy(), new TextUi());
            taskList.add("test", CommandType.TODO);
            assertEquals(1, taskList.taskCount);
            assertTrue(taskList.taskList.get(0) instanceof Todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add_deadlineTask_success() {
        try {
            TaskList taskList = new TaskList(new StorageDummy(), new TextUi());
            taskList.add("test", CommandType.DEADLINE, "2020-08-25 00:00");
            assertEquals(1, taskList.taskCount);
            assertTrue(taskList.taskList.get(0) instanceof Deadline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add_eventTask_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new StorageDummy(), new TextUi());
            taskList.add("test", CommandType.EVENT, "invalid time format", "2020-08-25 01:00");
        } catch (Exception e) {
            assertEquals("Sir, please check again to ensure you provide "
                    + "the correct arguments for command (event).", e.getMessage());
        }
    }
}

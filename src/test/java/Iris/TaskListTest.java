package iris;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_emptyDescription_exceptionThrown() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            TaskList taskListTester = new TaskList(taskList);
            TaskList.addTask(taskListTester, "deadline", "");
            fail("Expected exception was not thrown.");
        } catch (EmptyTaskDescriptorsException ex) {
            assertEquals("The description of a todo cannot be empty.", ex.toString());
        }
    }
}

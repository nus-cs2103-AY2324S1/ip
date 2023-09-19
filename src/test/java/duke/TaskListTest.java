package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void deleteTask_taskNumberNotAvailable_exceptionThrown() {
        try {
            TaskList test = new TaskList(new ArrayList<>());
            test.deleteTask(1);
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The delete command needs to be followed "
                    + "by an existing task number.", e.getMessage());
        }
    }

    @Test
    public void markTask_taskNumberNotAvailable_exceptionThrown() {
        try {
            TaskList test = new TaskList(new ArrayList<>());
            test.markTask(1);
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The mark command needs to be followed by an existing task number.", e.getMessage());
        }
    }
}

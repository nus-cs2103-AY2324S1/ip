package duke.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void removeTest_invalidIndex() {
        TaskList taskList = new TaskList();
        Assertions.assertEquals(taskList.remove(1), null);
    }

    @Test
    public void markTest_invalidIndex() {
        TaskList taskList = new TaskList();
        Assertions.assertEquals(taskList.mark(1), null);
    }
}
